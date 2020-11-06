package ru.abtank.servise;

import ru.abtank.representation.ProductTypeRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductTypeService {

    List<ProductTypeRepr> findAll();

    Optional<ProductTypeRepr> findById(Integer id);

    void deleteById(Integer id);

    void save(ProductTypeRepr productTypeRepr) throws IOException;
}
