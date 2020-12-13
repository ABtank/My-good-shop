package ru.abtank.persist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quick_desc", nullable = false)
    private String quickDescription;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "size")
    private Integer size;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(optional = false)
    private Brand brand;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Status status;

    @ManyToOne(optional = false)
    private ProductType type;

    @ManyToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinTable(name = "products_pictures",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<Picture> pictures;

    public Product() {
    }

    public Product(String name, String quickDescription, String description, Integer size, Integer discount, BigDecimal price, Brand brand, Category category, Status status, ProductType type, List<Picture> pictures) {
        this.name = name;
        this.quickDescription = quickDescription;
        this.description = description;
        this.size = size;
        this.discount = discount;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.status = status;
        this.type = type;
        this.pictures = pictures;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
