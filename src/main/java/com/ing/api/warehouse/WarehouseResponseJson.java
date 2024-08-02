package com.ing.api.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WarehouseResponseJson {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("address")
    private String address;

    @JsonProperty("capacity")
    private Long capacity;

    public Long getId() {
        return id;
    }

    public WarehouseResponseJson setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public WarehouseResponseJson setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getCapacity() {
        return capacity;
    }

    public WarehouseResponseJson setCapacity(Long capacity) {
        this.capacity = capacity;
        return this;
    }
}
