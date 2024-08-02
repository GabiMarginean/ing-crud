package com.ing.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PriceUpdateRequestJson {

    @JsonProperty("price")
    @NotNull(message = "price is required")
    @Positive(message = "price must be greater than zero")
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
