package ru.abtank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Product;
import ru.abtank.servises.ProductService;

import javax.persistence.Lob;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String productsPage(Model model) {
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
