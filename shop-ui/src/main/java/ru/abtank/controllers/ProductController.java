package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Product;
import ru.abtank.servises.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private BrandService brandService;
    private CategoryService categoryService;
    private StatusService statusService;
    private ProductTypeService productTypeService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService productService, BrandService brandService, CategoryService categoryService, StatusService statusService, ProductTypeService productTypeService) {
        this.productService = productService;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.statusService = statusService;
        this.productTypeService = productTypeService;
    }

    @GetMapping
    public String productsPage(Model model) {
        model.addAttribute("bannerPage", "Products");
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("product_types", productTypeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "products";
    }

    @GetMapping("/{id}")
    public String productPage(@PathVariable Long id, Model model) {
        model.addAttribute("bannerPage", "Product");
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("product_types", productTypeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        model.addAttribute("product", productService.findById(id).orElseThrow(() -> new NotFoundException(id.toString(), Product.class.getSimpleName())));
        return "product";
    }

}
