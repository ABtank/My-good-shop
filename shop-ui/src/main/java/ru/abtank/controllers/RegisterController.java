package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.User;
import ru.abtank.representations.UserRepr;
import ru.abtank.servises.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String createUser(Model model) {
        model.addAttribute("register", true);
        model.addAttribute("update", false);
        model.addAttribute("bannerPage", "Register");
        model.addAttribute("user", new UserRepr());
        return "register";
    }

    @PostMapping("/save")
    public String saveUser(Model model, UserRepr userRepr, RedirectAttributes redirectAttributes) {
        model.addAttribute("bannerPage", "Login");
        try {
           logger.info("try save user username {} email {}", userRepr.getUsername(), userRepr.getEmail());
            userService.save(userRepr);
        } catch (Exception e) {
            logger.error("Problem with creating or updating user", e);
            redirectAttributes.addFlashAttribute("error", true);
            if (userRepr.getId() == null) {
                return "redirect:/register";
            }
            return "redirect:/user/update";
        }
        return "redirect:/cart";
    }
}