package ru.abtank.representation;

import ru.abtank.persist.model.ProductType;

public class ProductTypeRepr {

    private Long id;

    private String name;

    public ProductTypeRepr() {
    }

    public ProductTypeRepr(ProductType productType) {
        this.id = productType.getId();
        this.name = productType.getName();
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
}
