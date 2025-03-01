package com.example.ExternalApi.client.domain;

import java.util.List;

public class ProductResponse {
    private List<Product> products;
    private int total;
    private int skip;
    private int limit;

    // Getters and Setters
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
