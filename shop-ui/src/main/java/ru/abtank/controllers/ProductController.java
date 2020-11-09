package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Product;
import ru.abtank.representations.BrandRepr;
import ru.abtank.representations.CategoryRepr;
import ru.abtank.representations.ProductRepr;
import ru.abtank.servises.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final StatusService statusService;
    private final ProductTypeService productTypeService;
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
    public String productsPage(Model model,
                               @RequestParam(defaultValue = "1", name = "page") Integer page,
                               @RequestParam Map<String, String> params,
                               @RequestParam MultiValueMap<String, String> checkboxParams) {
        if(page<1) page=1;
        Page<ProductRepr> productReprPage = productService.findAll(params, checkboxParams,page - 1, 9);
        model.addAttribute("productsPage", productReprPage);
        model.addAttribute("bannerPage", "Products");
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/{id}")
    public String productPage(@PathVariable Long id, Model model) {
        model.addAttribute("bannerPage", "Product");
        model.addAttribute("product", productService.findById(id).orElseThrow(() -> new NotFoundException(id.toString(), Product.class.getSimpleName())));
        return "product";
    }

}
