package com.ing.api.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WarehouseWithProductResponseJson {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("address")
    private String address;

    @JsonProperty("capacity")
    private Long capacity;

    @JsonProperty("remainingCapacity")
    private Long remainingCapacity;

    @JsonProperty("products")
    private List<WarehouseProductJsonResponse> products;

    public Long getId() {
        return id;
    }

    public WarehouseWithProductResponseJson setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public WarehouseWithProductResponseJson setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getCapacity() {
        return capacity;
    }

    public WarehouseWithProductResponseJson setCapacity(Long capacity) {
        this.capacity = capacity;
        return this;
    }

    public Long getRemainingCapacity() {
        return remainingCapacity;
    }

    public WarehouseWithProductResponseJson setRemainingCapacity(Long remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
        return this;
    }

    public List<WarehouseProductJsonResponse> getProducts() {
        return products;
    }

    public WarehouseWithProductResponseJson setProducts(List<WarehouseProductJsonResponse> products) {
        this.products = products;
        return this;
    }
}
