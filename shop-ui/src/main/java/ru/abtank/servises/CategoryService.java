package ru.abtank.servises;

import ru.abtank.representations.CategoryRepr;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryRepr> findAll();

    Optional<CategoryRepr> findById(Integer id);
}
