package com.ing.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductAssignWarehouseJsonRequest {

    @JsonProperty("warehouseId")
    @NotNull(message = "warehouseId is required")
    private Long warehouseId;

    @JsonProperty("quantity")
    @NotNull(message = "quantity is required")
    @Positive(message = "quantity must be greater than zero")
    private Long quantity;

    public Long getWarehouseId() {
        return warehouseId;
    }

    public ProductAssignWarehouseJsonRequest setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public ProductAssignWarehouseJsonRequest setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
