package com.ing.api.warehouse;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

public class WarehouseListResponseJson {

    @JsonUnwrapped
    private List<WarehouseResponseJson> warehouses;

    public List<WarehouseResponseJson> getWarehouses() {
        return warehouses;
    }

    public WarehouseListResponseJson setWarehouses(List<WarehouseResponseJson> warehouses) {
        this.warehouses = warehouses;
        return this;
    }
}
