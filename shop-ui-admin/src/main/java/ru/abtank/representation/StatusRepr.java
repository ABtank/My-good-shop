package ru.abtank.representation;

import ru.abtank.persist.model.Status;

public class StatusRepr {

    private Integer id;

    private String name;

    public StatusRepr() {
    }

    public StatusRepr(Status status) {
        this.id = status.getId();
        this.name = status.getName();
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
