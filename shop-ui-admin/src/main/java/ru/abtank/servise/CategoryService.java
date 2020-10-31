package ru.abtank.servise;

import ru.abtank.representation.CategoryRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryRepr> findAll();

    Optional<CategoryRepr> findById(Long id);

    void deleteById(Long id);

    void save(CategoryRepr categoryRepr) throws IOException;
}
