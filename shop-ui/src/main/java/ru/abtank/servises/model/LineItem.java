package ru.abtank.servises.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.abtank.representations.ProductRepr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class LineItem implements Serializable {

    private Long productId;

    private ProductRepr productRepr;

    private Integer qty;

    public LineItem(ProductRepr productRepr) {
        this.productId = productRepr.getId();
        this.productRepr = productRepr;
    }
    public LineItem() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductRepr getProductRepr() {
        return productRepr;
    }

    public void setProductRepr(ProductRepr productRepr) {
        this.productRepr = productRepr;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    @JsonIgnore
    public BigDecimal getTotal() {
        return productRepr.getDiscountPrice().multiply(new BigDecimal(qty));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return productId.equals(lineItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    private static String emptyToNull(String val) {
        return val == null ? "" : val;
    }
}
