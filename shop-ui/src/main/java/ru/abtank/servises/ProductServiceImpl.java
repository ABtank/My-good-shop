package ru.abtank.servises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abtank.persist.repositories.ProductRepository;
import ru.abtank.representations.ProductRepr;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductRepr> findAll() {
        logger.info("find all products");
        return productRepository.findAll().stream().map(ProductRepr :: new).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductRepr> findById(Long id) {
        logger.info("find one product");
        return productRepository.findById(id).map(ProductRepr::new);
    }
}
