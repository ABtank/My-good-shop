package ru.abtank.representations;

import ru.abtank.persist.model.Category;

public class CategoryRepr {

    private Integer id;

    private String name;

    public CategoryRepr(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public CategoryRepr() {
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
