package ru.abtank.servise;

import ru.abtank.representation.BrandRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<BrandRepr> findAll();

    Optional<BrandRepr> findById(Integer id);

    void deleteById(Integer id);

    void save(BrandRepr brandRepr) throws IOException;
}
