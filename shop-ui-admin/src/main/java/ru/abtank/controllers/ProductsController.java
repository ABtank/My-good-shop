package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Product;
import ru.abtank.representation.ProductRepr;
import ru.abtank.servise.*;


@Controller
@RequestMapping("/products")
public class ProductsController {

    private ProductService productService;
    private BrandService brandService;
    private CategoryService categoryService;
    private StatusService statusService;
    private ProductTypeService productTypeService;
    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    public void setStatusService(StatusService statusService) {
        this.statusService = statusService;
    }

    @Autowired
    public void setProductTypeService(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String productsPage(Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("create", true);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("product_types", productTypeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        model.addAttribute("product", new ProductRepr());
        logger.info("create new ProductRepr");
        return "product_form";
    }

    @GetMapping("/{id}/update")
    public String updateProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("update", true);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("product_types", productTypeService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        model.addAttribute("product", productService.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString(), Product.class.getSimpleName())));
        return "product_form";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Products");
        productService.deleteById(id);
        return "redirect:/products";
    }

    @PostMapping("/save")
    public String saveProduct(Model model, ProductRepr productRepr, RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", "Products");
        try {
            logger.info("try save product \ncategory {} \nbrand {} \nname {} \ntype {} \nstatus {} \nquick_desc {} \ndescription {} \nsize {} \ndiscount {} \nprice {}",
                    productRepr.getCategory().getName(), productRepr.getBrand().getName(), productRepr.getName(), productRepr.getType().getName(), productRepr.getStatus().getName(),
                    productRepr.getQuickDescription(), productRepr.getDescription(), productRepr.getSize(), productRepr.getDiscount(), productRepr.getPrice());
            productService.save(productRepr);
        } catch (Exception e) {
            if (productRepr.getId() == null) {
                logger.error("Problem with creating or updating product", e);
                return "redirect:/products/create";
            }
            return "redirect:/products/" + productRepr.getId() + "/update";
        }
        return "redirect:/products";
    }


}
