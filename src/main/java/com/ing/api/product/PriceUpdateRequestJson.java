package com.ing.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceUpdateRequestJson {

    @JsonProperty("price")
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
