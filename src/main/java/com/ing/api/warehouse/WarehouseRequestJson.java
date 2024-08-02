package com.ing.api.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WarehouseRequestJson {

    @JsonProperty("address")
    private String address;

    @JsonProperty("capacity")
    private Long capacity;

    public String getAddress() {
        return address;
    }

    public WarehouseRequestJson setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getCapacity() {
        return capacity;
    }

    public WarehouseRequestJson setCapacity(Long capacity) {
        this.capacity = capacity;
        return this;
    }
}

