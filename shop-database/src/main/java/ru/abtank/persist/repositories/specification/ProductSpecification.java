package ru.abtank.persist.repositories.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.abtank.persist.model.*;

import java.math.BigDecimal;

public final class ProductSpecification {

    public static Specification<Product> literalTrue() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }

    public static Specification<Product> nameLike(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> descriptionLike(String description) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }

    public static Specification<Product> priceLike(BigDecimal price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("price"), price);
    }

    public static Specification<Product> categoryLike(Category category) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Product> brandLike(Brand brand) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("brand"), brand);
    }

    public static Specification<Product> typeLike(ProductType type) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<Product> statusLike(Status status) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Product> afterMinPrice(BigDecimal minPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.gt(root.get("price"), minPrice);
    }

    public static Specification<Product> beforeMaxPrice(BigDecimal maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lt(root.get("price"), maxPrice);
    }

    public static Specification<Product> afterMinDiscount(Integer minDiscount) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.gt(root.get("discount"), minDiscount);
    }

    static Specification<Product> beforeMaxDiscount(Integer maxDiscount) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lt(root.get("discount"), maxDiscount);
    }
}
