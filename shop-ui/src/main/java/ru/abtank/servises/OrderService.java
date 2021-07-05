package ru.abtank.servises;

import ru.abtank.representations.OrderRepr;
import ru.abtank.servises.model.LineItem;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {

    List<OrderRepr> findAll();

    Optional<OrderRepr> findById(Long id);

    void deleteById(Long id);

    Long save(Long userId) throws IOException;
}
