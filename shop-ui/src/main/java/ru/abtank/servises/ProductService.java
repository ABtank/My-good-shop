package ru.abtank.servises;

import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import ru.abtank.persist.model.Product;
import ru.abtank.representations.ProductRepr;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();

    Optional<ProductRepr> findById(Long id);

    List<ProductRepr> findByBrandAndCategory(String brand, String category);

    List<ProductRepr> findByBrandNameAndCategoryNameAndAndTypeName(String brand, String category, String type);

    List<ProductRepr> findByType(String type);

    List<ProductRepr> findByBrand(String brand);

    List<ProductRepr> findByCategoryName(String category);

    Page<ProductRepr> findAll (Map<String, String> params, MultiValueMap<String, String> checkboxParam, int page, int size);
}
