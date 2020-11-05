package ru.abtank.servises;

import ru.abtank.representations.ProductRepr;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();

    Optional<ProductRepr> findById(Long id);
}
