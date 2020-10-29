package ru.abtank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/")
    public String indexPage(Model model){
        model.addAttribute("activePage", "None");
        return "index";
    }
}
