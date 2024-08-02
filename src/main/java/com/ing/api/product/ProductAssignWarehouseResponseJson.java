package com.ing.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductAssignWarehouseResponseJson {

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

    @JsonProperty("warehouses")
    private List<ProductWarehouseResponseJson> warehouses;

    public Long getId() {
        return id;
    }

    public ProductAssignWarehouseResponseJson setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductAssignWarehouseResponseJson setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductAssignWarehouseResponseJson setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductAssignWarehouseResponseJson setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ProductAssignWarehouseResponseJson setCategory(String category) {
        this.category = category;
        return this;
    }

    public List<ProductWarehouseResponseJson> getWarehouses() {
        return warehouses;
    }

    public ProductAssignWarehouseResponseJson setWarehouses(List<ProductWarehouseResponseJson> warehouses) {
        this.warehouses = warehouses;
        return this;
    }
}
