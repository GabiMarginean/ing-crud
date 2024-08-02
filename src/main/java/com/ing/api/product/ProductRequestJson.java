package com.ing.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductRequestJson {

    @JsonProperty("name")
    @NotBlank(message = "name is required")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    @NotNull(message = "price is required")
    @Positive(message = "price must be greater than zero")
    private Double price;

    @JsonProperty("category")
    @NotBlank(message = "category is mandatory")
    private String category;

    public String getName() {
        return name;
    }

    public ProductRequestJson setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductRequestJson setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductRequestJson setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ProductRequestJson setCategory(String category) {
        this.category = category;
        return this;
    }
}
