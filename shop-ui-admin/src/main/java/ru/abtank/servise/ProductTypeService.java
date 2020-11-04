package ru.abtank.servise;

import ru.abtank.representation.ProductTypeRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductTypeService {

    List<ProductTypeRepr> findAll();

    Optional<ProductTypeRepr> findById(Long id);

    void deleteById(Long id);

    void save(ProductTypeRepr productTypeRepr) throws IOException;
}
