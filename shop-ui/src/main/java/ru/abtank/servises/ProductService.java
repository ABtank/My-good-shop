package ru.abtank.servises;

import ru.abtank.persist.model.Product;
import ru.abtank.representations.ProductRepr;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();

    Optional<ProductRepr> findById(Long id);

    List<ProductRepr> findByBrandAndCategory(String brand, String category);

    List<ProductRepr> findByBrandNameAndCategoryNameAndAndTypeName(String brand, String category, String type);

    List<ProductRepr> findByType(String type);

    List<ProductRepr> findByBrand(String brand);

    List<ProductRepr> findByCategoryName(String category);
}
