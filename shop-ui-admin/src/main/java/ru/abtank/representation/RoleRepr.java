package ru.abtank.representation;

import ru.abtank.persist.model.Brand;
import ru.abtank.persist.model.Role;

public class RoleRepr {

    private Long id;

    private String name;

    public RoleRepr() {
    }

    public RoleRepr(Role role) {
        this.id = role.getId();
        this.name = role.getName();
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
