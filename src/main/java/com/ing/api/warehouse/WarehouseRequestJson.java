package com.ing.api.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WarehouseRequestJson {

    @JsonProperty("address")
    @NotBlank(message = "address is mandatory")
    private String address;

    @JsonProperty("capacity")
    @NotNull(message = "capacity is required")
    @Positive(message = "capacity must be greater than 0")
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

