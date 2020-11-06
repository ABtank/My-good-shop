package ru.abtank.representations;

import ru.abtank.persist.model.ProductType;

public class ProductTypeRepr {

    private Integer id;

    private String name;

    public ProductTypeRepr() {
    }

    public ProductTypeRepr(ProductType productType) {
        this.id = productType.getId();
        this.name = productType.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
