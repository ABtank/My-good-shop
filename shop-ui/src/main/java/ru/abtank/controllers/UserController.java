package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.User;
import ru.abtank.representations.UserRepr;
import ru.abtank.servises.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/update")
    public String updateUser(Model model, Principal principal) {
        model.addAttribute("bannerPage", "Update");
        model.addAttribute("register", false);
        model.addAttribute("update", true);
        UserRepr userRepr = userService.findByLogin(principal.getName())
                .orElseThrow(() -> new NotFoundException("-1", User.class.getSimpleName()));
        model.addAttribute("user", userRepr);
        logger.info("Update User login= {} id= {}", userRepr.getUsername(),userRepr.getId());
        return ("register");
    }

    @DeleteMapping("/delete")
    public String deleteUser(Principal principal) {
        UserRepr userRepr = userService.findByLogin(principal.getName())
                .orElseThrow(() -> new NotFoundException("-1", User.class.getSimpleName()));
        logger.info("Delete User login= {} id= {}", userRepr.getUsername(),userRepr.getId());
        userService.delete(userRepr.getId());
        return "redirect:/products";
    }
}
