package ru.abtank.servises;

import ru.abtank.representations.BrandRepr;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<BrandRepr> findAll();

    Optional<BrandRepr> findById(Integer id);

    Optional<BrandRepr> findByName(String name);
}
