package ru.abtank.representations;

import ru.abtank.persist.model.Brand;

public class BrandRepr {

    private Integer id;

    private String name;

    public BrandRepr(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
    }

    public BrandRepr() {
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
