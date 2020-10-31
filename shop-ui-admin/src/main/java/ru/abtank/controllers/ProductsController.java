package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.abtank.servise.ProductService;


@Controller
@RequestMapping("/products")
public class ProductsController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @GetMapping
    public String firstRequest(Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("products", productService.findAll());
        return "products";
    }


}
