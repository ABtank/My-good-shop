package ru.abtank.representations;

import ru.abtank.persist.model.Order;
import ru.abtank.persist.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepr {

    private Long id;

    private Long user_id;

    private BigDecimal price;

    private List<Long> itemsId;

    public OrderRepr() {
    }

    public OrderRepr(Order order) {
        this.id = order.getId();
        this.user_id = order.getId();
        this.price = order.getPrice();
        this.itemsId = order.getOrderItems().stream().map(OrderItem::getId).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Long> getItemsId() {
        return itemsId;
    }

    public void setItemsId(List<Long> itemsId) {
        this.itemsId = itemsId;
    }
}
