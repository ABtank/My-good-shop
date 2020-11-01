package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Category;
import ru.abtank.representation.CategoryRepr;
import ru.abtank.servise.CategoryService;


@Controller
@RequestMapping("/categories")
public class CategoriesController {

    private static final Logger logger = LoggerFactory.getLogger(CategoriesController.class);

    CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String categoriesPage(Model model) {
        model.addAttribute("activePage", "categories");
        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

    @GetMapping("/{id}/update")
    public String updateCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("actionPage", "Categories");
        model.addAttribute("update", true);
        model.addAttribute("category", categoryService.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString(), Category.class.getSimpleName())));
        return "category_form";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("actionPage", "Categories");
        categoryService.deleteById(id);
        return "redirect:/categories";
    }

    @GetMapping("/create")
    public String createCategory(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("actionPage", "Categories");
        model.addAttribute("category", new CategoryRepr());
        logger.info("create new CategoryRepr");
        return "category_form";
    }

    @PostMapping("/save")
    public String saveCategory(Model model, CategoryRepr categoryRepr, RedirectAttributes redirectAttributes) {
        model.addAttribute("actionPage", "Categories");
        try {
            categoryService.save(categoryRepr);
        } catch (Exception e) {
            logger.error("Problem with creating or updating category", e);
            if (categoryRepr.getId() == null) {
                return "redirect:/categories/create";
            }
            return "redirect:/categories/" + categoryRepr.getId() + "/update";
        }
        return "redirect:/categories";
    }
}
