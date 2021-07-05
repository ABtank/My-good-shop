package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.User;
import ru.abtank.representations.UserRepr;
import ru.abtank.servises.OrderService;
import ru.abtank.servises.UserService;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping ("/orders")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String ordersPage(Model model){
        model.addAttribute("activePage", "Orders");
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @PostMapping("/save")
    @Transactional
    public String save(Model model, Principal principal, RedirectAttributes redirectAttributes) throws IOException {
        long order_id;
        model.addAttribute("activePage", "Orders");
        if(principal != null){
            UserRepr user = userService.findByLogin(principal.getName()).orElseThrow(() -> new NotFoundException("user", User.class.getSimpleName()));
            order_id = orderService.save(user.getId());
        }else{
            return "redirect:/user_login";
        }
        logger.info(String.format("Заказ оформлен, номер заказа №%d", order_id));
        redirectAttributes.addAttribute("success", order_id);
        return "redirect:/cart";
    }
}
