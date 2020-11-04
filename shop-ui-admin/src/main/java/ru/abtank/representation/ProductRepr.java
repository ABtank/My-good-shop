package ru.abtank.representation;

import org.springframework.web.multipart.MultipartFile;
import ru.abtank.persist.model.*;
import ru.abtank.representations.PictureRepr;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepr {

    private Long id;

    private String name;

    private String quickDescription;

    private String description;

    private Integer size;

    private Integer discount;

    private BigDecimal price;

    private Category category;

    private Brand brand;

    private Status status;

    private ProductType type;
    //для сохранения в бд достаточно списка картинок
    private List<PictureRepr> pictures;

    //  Для получения информации о новой картинки от клиента
    private MultipartFile[] newPictures;

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
        this.category = product.getCategory();
        this.brand = product.getBrand();
        this.type = product.getType();
        this.status = product.getStatus();
        this.pictures = product.getPictures().stream()
                .map(PictureRepr::new)
                .collect(Collectors.toList());
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<PictureRepr> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureRepr> pictures) {
        this.pictures = pictures;
    }

    public MultipartFile[] getNewPictures() {
        return newPictures;
    }

    public void setNewPictures(MultipartFile[] newPictures) {
        this.newPictures = newPictures;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}
