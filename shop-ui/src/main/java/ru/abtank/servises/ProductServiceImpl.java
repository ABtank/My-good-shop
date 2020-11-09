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
import ru.abtank.persist.model.*;
import ru.abtank.persist.repositories.*;
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
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, StatusRepository statusRepository, BrandRepository brandRepository, ProductTypeRepository productTypeRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.statusRepository = statusRepository;
        this.brandRepository = brandRepository;
        this.productTypeRepository = productTypeRepository;
        this.categoryRepository = categoryRepository;
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
//        -----RadioButton-----
        if (params.containsKey("radio_status") && !params.get("radio_status").isBlank()) {
            Status status = statusRepository.findById(Integer.valueOf(params.get("radio_status"))).orElseThrow(() -> new NotFoundException(params.get("radio_status"), Status.class.getSimpleName()));
            spec = spec.and(ProductSpecification.statusLike(status));
            paramBuilder.append("&radio_status=" + params.get("radio_status"));
        }
        if (params.containsKey("radio_discount") && !params.get("radio_discount").isBlank()) {
            spec = spec.and(ProductSpecification.afterMinDiscount(Integer.valueOf(params.get("radio_discount"))));
            paramBuilder.append("&radio_discount=" + params.get("radio_discount"));
        }
        if (params.containsKey("radio_category") && !params.get("radio_category").isBlank()) {
            Category category = categoryRepository.findById(Integer.valueOf(params.get("radio_category"))).orElseThrow(() -> new NotFoundException(params.get("radio_category"), Category.class.getSimpleName()));
            spec = spec.and(ProductSpecification.categoryLike(category));
            paramBuilder.append("&radio_category=" + params.get("radio_category"));
        }
        if (params.containsKey("radio_brand") && !params.get("radio_brand").isBlank()) {
            Brand brand = brandRepository.findById(Integer.valueOf(params.get("radio_brand"))).orElseThrow(() -> new NotFoundException(params.get("radio_brand"), Brand.class.getSimpleName()));
            spec = spec.and(ProductSpecification.brandLike(brand));
            paramBuilder.append("&radio_brand=" + params.get("radio_brand"));
        }
//        -----Checkboxes-----
        if (checkboxParam.containsKey("checkbox_type")) {
            Specification<Product> specCheckType = Specification.where(null);
            ProductType productType;
            for (int i = 0; i < checkboxParam.get("checkbox_type").size(); i++) {
                int id = Integer.parseInt(checkboxParam.get("checkbox_type").get(i));
                productType = productTypeRepository.findById(id).orElseThrow(()->new NotFoundException(String.valueOf(id), ProductType.class.getSimpleName()));
                if (specCheckType == null) {
                    specCheckType = ProductSpecification.typeLike(productType);
                } else {
                    specCheckType = specCheckType.or(ProductSpecification.typeLike(productType));
                }
                paramBuilder.append("&checkbox_type=" + checkboxParam.get("checkbox_type").get(i));
            }
            spec = spec.and(specCheckType);
        }
        if (checkboxParam.containsKey("checkbox_brand")) {
            Specification<Product> specCheckBrand = Specification.where(null);
            Brand brand;
            for (int i = 0; i < checkboxParam.get("checkbox_brand").size(); i++) {
                int id = Integer.parseInt(checkboxParam.get("checkbox_brand").get(i));
                brand = brandRepository.findById(id).orElseThrow(()->new NotFoundException(String.valueOf(id), Brand.class.getSimpleName()));
                if (specCheckBrand == null) {
                    specCheckBrand = ProductSpecification.brandLike(brand);
                } else {
                    specCheckBrand = specCheckBrand.or(ProductSpecification.brandLike(brand));
                }
                paramBuilder.append("&checkbox_brand=" + checkboxParam.get("checkbox_brand").get(i));
            }
            spec = spec.and(specCheckBrand);
        }
        logger.info(paramBuilder.toString());
        return productRepository.findAll(spec, PageRequest.of(page, size)).map(ProductRepr::new);
    }
}
