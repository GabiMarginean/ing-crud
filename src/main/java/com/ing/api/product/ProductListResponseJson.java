package com.ing.api.product;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;
import java.util.List;


public class ProductListResponseJson implements Serializable {

    @JsonUnwrapped
    private List<ProductResponseJson> products;

    public List<ProductResponseJson> getProducts() {
        return products;
    }

    public ProductListResponseJson setProducts(List<ProductResponseJson> products) {
        this.products = products;
        return this;
    }
}
