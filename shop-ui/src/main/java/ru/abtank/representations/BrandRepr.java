package ru.abtank.representations;

import ru.abtank.persist.model.Brand;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandRepr brandRepr = (BrandRepr) o;
        return Objects.equals(id, brandRepr.id) &&
                Objects.equals(name, brandRepr.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
