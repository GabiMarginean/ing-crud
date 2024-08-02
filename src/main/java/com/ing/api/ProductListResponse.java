package com.ing.api;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;


public class ProductListResponse {

    @JsonUnwrapped
    private List<ProductResponseJson> products;

    public List<ProductResponseJson> getProducts() {
        return products;
    }

    public ProductListResponse setProducts(List<ProductResponseJson> products) {
        this.products = products;
        return this;
    }
}
