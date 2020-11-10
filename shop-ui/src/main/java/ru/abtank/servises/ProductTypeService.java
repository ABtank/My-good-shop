package ru.abtank.servises;

import ru.abtank.representations.ProductTypeRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductTypeService {

    List<ProductTypeRepr> findAll();

    Optional<ProductTypeRepr> findById(Integer id);
}
