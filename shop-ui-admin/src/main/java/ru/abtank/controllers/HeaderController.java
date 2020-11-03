package ru.abtank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.abtank.servise.*;

import java.security.Principal;

@ControllerAdvice
public class HeaderController {

    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final RoleService roleService;

    @Autowired
    public HeaderController(ProductService productService, UserService userService, CategoryService categoryService, BrandService brandService, RoleService roleService) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.roleService = roleService;
    }

    @ModelAttribute
//    @Secured("ROLE_ADMIN")
    public void countAll(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("count_products", productService.findAll().size());
            model.addAttribute("count_categories", categoryService.findAll().size());
            model.addAttribute("count_users", userService.findAll().size());
            model.addAttribute("count_brands", brandService.findAll().size());
            model.addAttribute("count_roles", roleService.findAll().size());
        }
    }
}
