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
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.getModel().put("exceptionMessage", ex.getMessage());
        modelAndView.getModel().put("bannerPage", "404");
        return modelAndView;
    }

    @ExceptionHandler
    public ModelAndView internalServerExceptionHandler(ServerInternalException ex) {
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.getModel().put("exceptionMessage", ex.getMessage());
        modelAndView.getModel().put("bannerPage", "404");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
