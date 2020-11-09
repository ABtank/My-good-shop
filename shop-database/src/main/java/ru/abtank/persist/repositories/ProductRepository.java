package ru.abtank.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.abtank.persist.model.Brand;
import ru.abtank.persist.model.Category;
import ru.abtank.persist.model.Product;
import ru.abtank.persist.model.ProductType;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findByBrandNameAndCategoryName(String brand, String category);
    List<Product> findByBrandNameAndCategoryNameAndAndTypeName(String brand, String category, String type);
    List<Product> findByCategoryName(String category);
    List<Product> findByTypeName(String type);
    List<Product> findByBrandName(String brand);
}
