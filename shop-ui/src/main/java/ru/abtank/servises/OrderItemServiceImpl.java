package ru.abtank.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.OrderItem;
import ru.abtank.persist.repositories.OrderItemRepository;
import ru.abtank.persist.repositories.ProductRepository;
import ru.abtank.representations.OrderItemRepr;
import ru.abtank.servises.model.LineItem;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItemRepr> findAll() {
        return orderItemRepository.findAll().stream().map(OrderItemRepr::new).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderItemRepr> findById(Long id) {
        return orderItemRepository.findById(id).map(OrderItemRepr::new);
    }

    @Override
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItem save(LineItem lineItem) throws IOException {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productRepository.findById(lineItem.getProductId()).orElseThrow(() -> new NotFoundException(String.valueOf(lineItem.getProductId()), OrderItem.class.getSimpleName())));
        orderItem.setQuantity(lineItem.getQty());
        orderItem.setPrice(lineItem.getTotal());
        return orderItem;
    }
}
