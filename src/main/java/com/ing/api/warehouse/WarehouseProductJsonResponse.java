package com.ing.api.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WarehouseProductJsonResponse implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("quantity")
    private Long quantity;

    public Long getId() {
        return id;
    }

    public WarehouseProductJsonResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WarehouseProductJsonResponse setName(String name) {
        this.name = name;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public WarehouseProductJsonResponse setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
