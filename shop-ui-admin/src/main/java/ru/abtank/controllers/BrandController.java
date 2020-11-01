package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.abtank.persist.model.Brand;
import ru.abtank.representation.BrandRepr;
import ru.abtank.servise.BrandService;
import ru.abtank.servise.BrandServiceImpl;

@Controller
@RequestMapping("/brands")
public class BrandController {

    BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @GetMapping
    public String brandsPage(Model model) {
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brands", brandService.findAll());
        return "brands";
    }

    @GetMapping("/{id}/update")
    public String updateBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Brands");
        model.addAttribute("update", true);
        model.addAttribute("brand", brandService.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString(), Brand.class.getSimpleName())));
        return "brand_form";
    }

    @GetMapping("/create")
    public String brandCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brand", new BrandRepr());
        logger.info("create new BrandRepr");
        return "brand_form";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Brands");
        brandService.deleteById(id);
        return "redirect:/brands";
    }

    @PostMapping("/save")
    public String saveBrand(Model model, BrandRepr brand, RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", "Brands");
        logger.info("come to try");
        try {
            logger.info("try save");
            brandService.save(brand);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating brand", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (brand.getId() == null) {
                return "redirect:/brands/create";
            }
            return "redirect:/brands/" + brand.getId() + "/update";
        }
        return "redirect:/brands";

    }

}
