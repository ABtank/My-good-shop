package ru.abtank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.abtank.representations.BrandRepr;
import ru.abtank.representations.CategoryRepr;
import ru.abtank.representations.ProductRepr;
import ru.abtank.servises.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class HeaderController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final StatusService statusService;
    private final ProductTypeService productTypeService;
    private final CartService cartService;

    @Autowired
    public HeaderController(ProductService productService, CategoryService categoryService, BrandService brandService, StatusService statusService, ProductTypeService productTypeService, CartService cartService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.statusService = statusService;
        this.productTypeService = productTypeService;
        this.cartService = cartService;
    }

    @ModelAttribute
//    @Secured("ROLE_ADMIN")
    public void countAll(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("product_types", productTypeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        model.addAttribute("count_products", productService.findAll().size());
        model.addAttribute("count_categories", categoryService.findAll().size());
        model.addAttribute("count_brands", brandService.findAll().size());
        model.addAttribute("count_product_types", productTypeService.findAll().size());
        Map<CategoryRepr, Set<BrandRepr>> categoryAndBrands = new LinkedHashMap<>();
        categoryService.findAll().stream().forEach(cat -> categoryAndBrands.put(cat, productService.findByCategoryName(cat.getName()).stream().map(ProductRepr::getBrand).map(br -> brandService.findByName(br).get()).collect(Collectors.toSet())));
        model.addAttribute("categoryAndBrands", categoryAndBrands);
        model.addAttribute("cartSubTotal", cartService.getSubTotal());
    }
}
