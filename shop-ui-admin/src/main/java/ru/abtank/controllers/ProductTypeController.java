package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Brand;
import ru.abtank.persist.model.ProductType;
import ru.abtank.representation.BrandRepr;
import ru.abtank.representation.ProductTypeRepr;
import ru.abtank.servise.ProductTypeService;

@Controller
@RequestMapping("/product_types")
public class ProductTypeController {

    ProductTypeService productTypeService;

    @Autowired
    public void setProductTypeService(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductTypeController.class);

    @GetMapping
    public String productTypesPage(Model model) {
        model.addAttribute("activePage", "Product_types");
        model.addAttribute("product_types", productTypeService.findAll());
        return "product_types";
    }

    @GetMapping("/{id}/update")
    public String updateProductTypes(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("activePage", "Product_types");
        model.addAttribute("update", true);
        model.addAttribute("product_type", productTypeService.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString(), ProductType.class.getSimpleName())));
        return "product_type_form";
    }

    @GetMapping("/create")
    public String ProductTypesCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Product_types");
        model.addAttribute("product_type", new ProductTypeRepr());
        logger.info("create new ProductTypeRepr");
        return "product_type_form";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteProductTypes(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("activePage", "Product_types");
        productTypeService.deleteById(id);
        return "redirect:/product_types";
    }

    @PostMapping("/save")
    public String saveProductTypes(Model model, ProductTypeRepr productType, RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", "Product_types");
        logger.info("come to try");
        try {
            logger.info("try save");
            productTypeService.save(productType);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating productType", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (productType.getId() == null) {
                return "redirect:/product_types/create";
            }
            return "redirect:/product_types/" + productType.getId() + "/update";
        }
        return "redirect:/product_types";

    }

}
