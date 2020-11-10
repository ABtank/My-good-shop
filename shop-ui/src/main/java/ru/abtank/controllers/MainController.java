package ru.abtank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {


    @GetMapping
    public String indexPage() {
        return "index";
    }

    @GetMapping("/404")
    public String errorPage() {
        return "404";
    }
    @GetMapping("/typo")
    public String typoPage() {
        return "typo";
    }
    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }
}
