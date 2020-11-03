package ru.abtank.servise;

import ru.abtank.representation.BrandRepr;
import ru.abtank.representation.ProductRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<BrandRepr> findAll();

    Optional<BrandRepr> findById(Long id);

    void deleteById(Long id);

    void save(BrandRepr brandRepr) throws IOException;
}
