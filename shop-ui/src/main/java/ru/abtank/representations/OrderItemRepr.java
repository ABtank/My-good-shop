package ru.abtank.representations;

import ru.abtank.persist.model.OrderItem;
import ru.abtank.servises.model.LineItem;

import java.math.BigDecimal;

public class OrderItemRepr {

    private Long id;

    private String product;

    private Integer quantity;

    private BigDecimal price;

    public OrderItemRepr() {
    }

    public OrderItemRepr(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.product = orderItem.getProduct().getName();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
