package com.ing.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequestJson {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("category")
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
