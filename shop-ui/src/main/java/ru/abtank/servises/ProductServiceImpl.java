package ru.abtank.servises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.abtank.persist.model.Product;
import ru.abtank.persist.repositories.ProductRepository;
import ru.abtank.persist.repositories.specification.ProductSpecification;
import ru.abtank.representations.ProductRepr;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductRepr> findAll() {
        logger.info("find all products");
        return productRepository.findAll().stream().map(ProductRepr::new).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductRepr> findById(Long id) {
        logger.info("find one product");
        return productRepository.findById(id).map(ProductRepr::new);
    }

    @Override
    public List<ProductRepr> findByType(String type) {
        return productRepository.findByTypeName(type).stream().map(ProductRepr::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductRepr> findByBrand(String brand) {
        return productRepository.findByBrandName(brand).stream().map(ProductRepr::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductRepr> findByCategoryName(String category) {
        return productRepository.findByCategoryName(category).stream().map(ProductRepr::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductRepr> findByBrandAndCategory(String brand, String category) {
        logger.info(" findByBrandNameAndCategoryName");
        return productRepository.findByBrandNameAndCategoryName(brand, category).stream().map(ProductRepr::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductRepr> findByBrandNameAndCategoryNameAndAndTypeName(String brand, String category, String type) {
        return productRepository.findByBrandNameAndCategoryNameAndAndTypeName(brand, category, type).stream().map(ProductRepr::new).collect(Collectors.toList());
    }

    public Page<ProductRepr> findAll (int page, int size) {
        Specification<Product> spec = ProductSpecification.literalTrue();
        return productRepository.findAll(spec, PageRequest.of(page, size)).map(ProductRepr::new);
    }
}
