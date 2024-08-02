package com.ing.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ProductWarehouseResponseJson implements Serializable {

    @JsonProperty("warehouseId")
    private Long warehouseId;

    @JsonProperty("quantity")
    private Long quantity;

    public Long getWarehouseId() {
        return warehouseId;
    }

    public ProductWarehouseResponseJson setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public ProductWarehouseResponseJson setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
