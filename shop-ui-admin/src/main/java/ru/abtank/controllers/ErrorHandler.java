package ru.abtank.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.exceptions.ServerInternalException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found_error");
        modelAndView.getModel().put("exceptionMessage", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler
    public ModelAndView internalServerExceptionHandler(ServerInternalException ex) {
        ModelAndView modelAndView = new ModelAndView("internal_server_error");
        modelAndView.getModel().put("exceptionMessage", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
