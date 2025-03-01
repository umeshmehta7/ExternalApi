package com.example.ExternalApi.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Recipe {
    @JsonProperty("id")
    private int productId;
    private String cuisine;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
