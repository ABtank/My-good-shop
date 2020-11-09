package ru.abtank.persist.repositories.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.abtank.persist.model.Product;

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

    public static Specification<Product> categoryLike(Integer category_id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category_id"), category_id);
    }

    public static Specification<Product> brandLike(Integer brand_id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("brand_id"), brand_id);
    }

    public static Specification<Product> typeLike(Integer type_id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("type_id"), type_id);
    }

    public static Specification<Product> statusLike(Integer status_id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status_id"), status_id);
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
