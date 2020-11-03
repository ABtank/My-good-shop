package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/admin")
    public String indexPage(Model model, Principal principal) {
        if (principal != null) {
            System.out.println(((Authentication) principal).getAuthorities());
        }
        return "test";
    }

    @GetMapping("/")
    public String welcomePage() {
        return "index";
    }

    @GetMapping("/auth")
    @ResponseBody
    public String authPage(Principal principal) {
        if (principal != null) {
            System.out.println(((Authentication) principal).getAuthorities());
        }
        return "login" + principal.getName();
    }

    @GetMapping("/deleteusers")
    public String deleteUsersPage(Principal principal) {
        if (principal != null) {
            System.out.println(((Authentication) principal).getAuthorities());
        }
        return "test";
    }
}
