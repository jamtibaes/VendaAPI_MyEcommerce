package com.letscode.ecommerce.vendaapi.entity;

public class ItemsEntity {

    private Long productId;
    private Integer quantity;
    private Double price;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemsEntity{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
