package ru.abtank.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abtank.persist.model.Order;
import ru.abtank.persist.model.OrderItem;
import ru.abtank.persist.repositories.OrderItemRepository;
import ru.abtank.persist.repositories.OrderRepository;
import ru.abtank.persist.repositories.UserRepository;
import ru.abtank.representations.OrderRepr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final UserRepository userRepository;
    private final CartService cartService;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService, UserRepository userRepository, CartService cartService, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderRepr> findAll() {
        return orderRepository.findAll().stream().map(OrderRepr::new).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderRepr> findById(Long id) {
        return orderRepository.findById(id).map(OrderRepr::new);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Long save(Long userId) {
        List<OrderItem> orderItems = new ArrayList<>();
        cartService.getLineItems().forEach(line -> {
            try {
                orderItems.add(orderItemService.save(line));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Order order = new Order(userRepository.findById(userId).get(), orderItems, cartService.getSubTotal());
        orderRepository.save(order);
        orderItems.stream().forEach(o -> orderItemRepository.save(o));
        cartService.clearCart();
        return order.getId();
    }
}
