package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.abtank.persist.model.Brand;
import ru.abtank.persist.model.Role;
import ru.abtank.representation.BrandRepr;
import ru.abtank.representation.RoleRepr;
import ru.abtank.servise.BrandServiceImpl;
import ru.abtank.servise.RoleServiceImpl;

@Controller
@RequestMapping("/roles")
public class RoleController {

    RoleServiceImpl roleService;

    @Autowired
    public void setRoleService(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @GetMapping
    public String rolesPage(Model model) {
        model.addAttribute("activePage", "Roles");
        model.addAttribute("roles", roleService.findAll());
        return "roles";
    }

    @GetMapping("/{id}/update")
    public String updateBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Roles");
        model.addAttribute("update", true);
        model.addAttribute("role", roleService.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString(), Role.class.getSimpleName())));
        return "brand_form";
    }

    @GetMapping("/create")
    public String brandCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", new RoleRepr());
        logger.info("create new RoleRepr");
        return "role_form";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Roles");
        roleService.deleteById(id);
        return "redirect:/roles";
    }

    @PostMapping("/save")
    public String saveBrand(Model model, RoleRepr roleRepr, RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", "Roles");
        logger.info("come to try");
        try {
            logger.info("try save role");
            roleService.save(roleRepr);
        } catch (Exception e) {
            logger.error("Problem with creating or updating role", e);
            redirectAttributes.addFlashAttribute("error", true);
            if (roleRepr.getId() == null) {
                return "redirect:/roles/create";
            }
            return "redirect:/roles/" + roleRepr.getId() + "/update";
        }
        return "redirect:/roles";

    }

}
