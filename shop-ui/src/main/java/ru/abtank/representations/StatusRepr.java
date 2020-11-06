package ru.abtank.representations;

import ru.abtank.persist.model.Status;

public class StatusRepr {

    private Long id;

    private String name;

    public StatusRepr() {
    }

    public StatusRepr(Status status) {
        this.id = status.getId();
        this.name = status.getName();
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
