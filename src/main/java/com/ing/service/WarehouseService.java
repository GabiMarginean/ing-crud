package com.ing.service;

import com.ing.api.warehouse.WarehouseListResponseJson;
import com.ing.api.warehouse.WarehouseRequestJson;
import com.ing.api.warehouse.WarehouseResponseJson;
import com.ing.domain.Warehouse;
import com.ing.error.ErrorCode;
import com.ing.repository.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class WarehouseService {

    public static final String WAREHOUSE_ENTITY = "Warehouse";
    @Autowired
    private WarehouseRepository warehouseRepository;

    private static final Logger logger = LoggerFactory.getLogger(WarehouseService.class);

    public WarehouseListResponseJson getAllWarehouses() {
        return getListResponse(warehouseRepository.findAll());
    }

    public WarehouseResponseJson getWarehouse(Long warehouseId) {
        Warehouse warehouse = retrieveWarehouse(warehouseId);
        return toJsonResponse(warehouse);
    }

    public WarehouseResponseJson createWarehouse(WarehouseRequestJson warehouseRequest) {
        Warehouse warehouse = toEntity(warehouseRequest);
        return toJsonResponse(warehouseRepository.save(warehouse));
    }

    public WarehouseResponseJson updateWarehouse(Long warehouseId, WarehouseRequestJson warehouseRequest) {
        retrieveWarehouse(warehouseId);
        Warehouse warehouse = toEntity(warehouseRequest).setId(warehouseId);
        return toJsonResponse(warehouseRepository.save(warehouse));
    }

    public void deleteWarehouse(Long warehouseId) {
        Warehouse warehouse = retrieveWarehouse(warehouseId);
        warehouseRepository.delete(warehouse);
    }

    private Warehouse retrieveWarehouse(Long warehouseId) {
        return warehouseRepository.findById(warehouseId).orElseThrow(
                () -> {
                    logger.warn("Tried to retrieve invalid warehouse with id: {}", warehouseId);
                    return new RuntimeException(MessageFormat.format(ErrorCode.ENTITY_NOT_FOUND.getMessage(), WAREHOUSE_ENTITY, warehouseId));
                });
    }

    private WarehouseListResponseJson getListResponse(List<Warehouse> warehouses) {
        return new WarehouseListResponseJson()
                .setWarehouses(warehouses.stream()
                        .map(this::toJsonResponse)
                        .toList());

    }

    private WarehouseResponseJson toJsonResponse(Warehouse warehouse) {
        return new WarehouseResponseJson()
                .setId(warehouse.getId())
                .setAddress(warehouse.getAddress())
                .setCapacity(warehouse.getCapacity());
    }

    private Warehouse toEntity(WarehouseRequestJson warehouseRequest) {
        return new Warehouse()
                .setAddress(warehouseRequest.getAddress())
                .setCapacity(warehouseRequest.getCapacity());
    }
}
