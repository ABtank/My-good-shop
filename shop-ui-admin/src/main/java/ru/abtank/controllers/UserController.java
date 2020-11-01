package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.abtank.persist.model.User;
import ru.abtank.representation.UserRepr;
import ru.abtank.servise.RoleService;
import ru.abtank.servise.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String usersPage(Model model) {
        model.addAttribute("activePage", "User");
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("create", true);
        model.addAttribute("user", new UserRepr());
        model.addAttribute("roles", roleService.findAll());
        return "user_form";
    }

    @GetMapping("/{id}/update")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("update", true);
        model.addAttribute("user", userService.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString(), User.class.getSimpleName())));
        model.addAttribute("roles", roleService.findAll());
        return ("user_form");
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Users");
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping("/save")
    public String saveUser(Model model, UserRepr userRepr, RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", "Users");
        try {
            logger.info("try save user");
            userService.save(userRepr);
        } catch (Exception e) {
            logger.error("Problem with creating or updating user", e);
            redirectAttributes.addFlashAttribute("error", true);
            if (userRepr.getId() == null) {
                return "redirect:/users/create";
            }
            return "redirect:/users/" + userRepr.getId() + "/update";
        }
        return "redirect:/users";
    }
}
