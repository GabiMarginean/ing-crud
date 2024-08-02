package com.ing.controller;

import com.ing.api.warehouse.WarehouseListResponseJson;
import com.ing.api.warehouse.WarehouseRequestJson;
import com.ing.api.warehouse.WarehouseResponseJson;
import com.ing.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public WarehouseListResponseJson getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{warehouseId}")
    public WarehouseResponseJson getWarehouse(@PathVariable Long warehouseId) {
        return warehouseService.getWarehouse(warehouseId);
    }

    @PostMapping
    public WarehouseResponseJson createWarehouse(@RequestBody WarehouseRequestJson warehouseRequest) {
        return warehouseService.createWarehouse(warehouseRequest);
    }

    @PutMapping("/{warehouseId}")
    public WarehouseResponseJson updateWarehouse(@PathVariable Long warehouseId,
                                                 @RequestBody WarehouseRequestJson warehouseRequest) {
        return warehouseService.updateWarehouse(warehouseId, warehouseRequest);
    }

    @DeleteMapping("/{warehouseId}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);
        return ResponseEntity.noContent().build();
    }
}
