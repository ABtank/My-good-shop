package ru.abtank.servise;

public class Stock {

    private Long id;

    private Integer count;

    public Stock(Long id, Integer count) {
        this.id = id;
        this.count = count;
    }

    public Stock() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
