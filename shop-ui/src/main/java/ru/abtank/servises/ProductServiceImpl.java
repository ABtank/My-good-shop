package ru.abtank.servises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Product;
import ru.abtank.persist.model.ProductType;
import ru.abtank.persist.model.Status;
import ru.abtank.persist.repositories.BrandRepository;
import ru.abtank.persist.repositories.ProductRepository;
import ru.abtank.persist.repositories.ProductTypeRepository;
import ru.abtank.persist.repositories.StatusRepository;
import ru.abtank.persist.repositories.specification.ProductSpecification;
import ru.abtank.representations.ProductRepr;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final StatusRepository statusRepository;
    private final BrandRepository brandRepository;
    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, StatusRepository statusRepository, BrandRepository brandRepository, ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.statusRepository = statusRepository;
        this.brandRepository = brandRepository;
        this.productTypeRepository = productTypeRepository;
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

    @Override
    @Transactional
    public Page<ProductRepr> findAll(Map<String, String> params, MultiValueMap<String, String> checkboxParam, int page, int size) {
        Specification<Product> spec = ProductSpecification.literalTrue();
        StringBuilder paramBuilder = new StringBuilder();
        if (params.containsKey("radio_status") && !params.get("radio_status").isBlank()) {
            Status status = statusRepository.findById(Integer.valueOf(params.get("radio_status"))).orElseThrow(() -> new NotFoundException(params.get("radio_status"), Status.class.getSimpleName()));
            spec = spec.and(ProductSpecification.statusLike(status));
            paramBuilder.append("&radio_status=" + params.get("radio_status"));
        }
        if (params.containsKey("radio_discount") && !params.get("radio_discount").isBlank()) {
            spec = spec.and(ProductSpecification.afterMinDiscount(Integer.valueOf(params.get("radio_discount"))));
            paramBuilder.append("&radio_discount=" + params.get("radio_discount"));
        }

        if (checkboxParam.containsKey("checkbox_type")) {
            Specification<Product> specCheck = Specification.where(null);
            ProductType productType;
            for (int i = 0; i < checkboxParam.get("checkbox_type").size(); i++) {
                int id = Integer.parseInt(checkboxParam.get("checkbox_type").get(i));
                productType = productTypeRepository.findById(id).orElseThrow(()->new NotFoundException(String.valueOf(id), ProductType.class.getSimpleName()));
                if (specCheck == null) {
                    specCheck = ProductSpecification.typeLike(productType);
                } else {
                    specCheck = specCheck.or(ProductSpecification.typeLike(productType));
                }
                paramBuilder.append("&checkbox_type=" + checkboxParam.get("checkbox_type").get(i));
            }
            spec = spec.and(specCheck);
        }
        logger.info(paramBuilder.toString());
        return productRepository.findAll(spec, PageRequest.of(page, size)).map(ProductRepr::new);
    }
}
