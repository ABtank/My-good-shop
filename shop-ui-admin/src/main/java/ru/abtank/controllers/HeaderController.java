package ru.abtank.controllers;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.abtank.servise.*;

import java.security.Principal;

@ControllerAdvice
public class HeaderController {

    private static final Logger logger = LoggerFactory.getLogger(HeaderController.class);

    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final StatusService statusService;
    private final ProductTypeService productTypeService;
    private final RoleService roleService;

    private final EurekaClient eurekaClient;

    @Autowired
    public HeaderController(ProductService productService, UserService userService, CategoryService categoryService, BrandService brandService, StatusService statusService, ProductTypeService productTypeService, RoleService roleService, EurekaClient eurekaClient) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.statusService = statusService;
        this.productTypeService = productTypeService;
        this.roleService = roleService;
        this.eurekaClient = eurekaClient;
    }

    @ModelAttribute
//    @Secured("ROLE_ADMIN")
    public void countAll(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("count_products", productService.findAll().size());
            model.addAttribute("count_categories", categoryService.findAll().size());
            model.addAttribute("count_users", userService.findAll().size());
            model.addAttribute("count_brands", brandService.findAll().size());
            model.addAttribute("count_statuses", statusService.findAll().size());
            model.addAttribute("count_product_types", productTypeService.findAll().size());
            model.addAttribute("count_roles", roleService.findAll().size());
        }
        InstanceInfo server = eurekaClient.getNextServerFromEureka("GATEWAY-SERVICE", false);
        logger.info("Picture service instance: " + server);
        model.addAttribute("pictureServiceUrl", server.getHomePageUrl() + "picture-service");
    }
}
