package com.letscode.ecommerce.vendaapi.entity;

import java.util.ArrayList;
import java.util.List;

public class CartEntity {

    private String id;
    private List<ItemsEntity> productList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ItemsEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ItemsEntity> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "id='" + id + '\'' +
                ", productList=" + productList +
                '}';
    }
}
