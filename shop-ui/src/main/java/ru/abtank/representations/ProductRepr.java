package ru.abtank.representations;

import ru.abtank.persist.model.Picture;
import ru.abtank.persist.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ROUND_DOWN;

public class ProductRepr {

    private Long id;

    private String name;

    private String quickDescription;

    private String description;

    private Integer size;

    private Integer discount;

    private BigDecimal price;
    private BigDecimal discountPrice;

    private String category;

    private String brand;

    private String status;

    private String type;

    private List<Long> picturesId;

    public ProductRepr() {
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.quickDescription = product.getQuickDescription();
        this.description = product.getDescription();
        this.size = product.getSize();
        this.discount = product.getDiscount();
        this.price = product.getPrice();
        this.category = product.getCategory().getName();
        this.brand = product.getBrand().getName();
        this.status = product.getStatus().getName();
        this.type = product.getType().getName();
        this.picturesId = product.getPictures().stream().map(Picture::getId).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuickDescription() {
        return quickDescription;
    }

    public void setQuickDescription(String quickDescription) {
        this.quickDescription = quickDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Long> getPicturesId() {
        return picturesId;
    }

    public void setPicturesId(List<Long> picturesId) {
        this.picturesId = picturesId;
    }

    public BigDecimal getDiscountPrice() {
        discountPrice = price.subtract(new BigDecimal(price.floatValue() * discount * 0.01));
        return discountPrice.setScale(2, ROUND_DOWN);
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }
}
