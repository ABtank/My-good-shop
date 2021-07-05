package ru.abtank.servises;

import ru.abtank.persist.model.OrderItem;
import ru.abtank.representations.OrderItemRepr;
import ru.abtank.servises.model.LineItem;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface OrderItemService {

    List<OrderItemRepr> findAll();

    Optional<OrderItemRepr> findById(Long id);

    void deleteById(Long id);

    OrderItem save(LineItem lineItem) throws IOException;
}
