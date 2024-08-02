package com.ing.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class ProductResponseJson implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("category")
    private String category;

    public Long getId() {
        return id;
    }

    public ProductResponseJson setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductResponseJson setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductResponseJson setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductResponseJson setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ProductResponseJson setCategory(String category) {
        this.category = category;
        return this;
    }
}
