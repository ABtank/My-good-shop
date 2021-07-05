package ru.abtank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user_login")
public class LoginController {

    @GetMapping
    public String userLoginPage(Model model){
        model.addAttribute("bannerPage", "Login");
        return "login";
    }
}
