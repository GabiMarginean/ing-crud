package com.ing.service;

import com.ing.api.warehouse.*;
import com.ing.domain.Product;
import com.ing.domain.ProductWarehouse;
import com.ing.domain.Warehouse;
import com.ing.error.CustomException;
import com.ing.error.ErrorCode;
import com.ing.repository.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class WarehouseService {

    public static final String WAREHOUSE_ENTITY = "Warehouse";
    @Autowired
    private WarehouseRepository warehouseRepository;

    private static final Logger logger = LoggerFactory.getLogger(WarehouseService.class);

    @Cacheable(value = "warehouses", key = "'allWarehouses'")
    public WarehouseListResponseJson getAllWarehouses() {
        return getListResponse(warehouseRepository.findAll());
    }

    @Cacheable(value = "warehouses", key = "#warehouseId")
    public WarehouseResponseJson getWarehouse(Long warehouseId) {
        Warehouse warehouse = retrieveWarehouse(warehouseId);
        return toJsonResponse(warehouse);
    }

    public WarehouseWithProductResponseJson getWarehouseWithProducts(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findWithProductsById(warehouseId);
        Long usedCapacity = warehouse.getProductWarehouses().stream()
                .mapToLong(ProductWarehouse::getQuantity)
                .sum();

        return getResponseWithProducts(warehouse, usedCapacity);
    }

    @CachePut(value = "warehouses", key = "#result.id")
    @CacheEvict(value = "warehouses", key = "'allWarehouses'")
    public WarehouseResponseJson createWarehouse(WarehouseRequestJson warehouseRequest) {
        Warehouse warehouse = toEntity(warehouseRequest);
        return toJsonResponse(warehouseRepository.save(warehouse));
    }

    @CachePut(value = "warehouses", key = "#warehouseId.id")
    @CacheEvict(value = "warehouses", key = "'allWarehouses'")
    public WarehouseResponseJson updateWarehouse(Long warehouseId, WarehouseRequestJson warehouseRequest) {
        retrieveWarehouse(warehouseId);
        Warehouse warehouse = toEntity(warehouseRequest).setId(warehouseId);
        return toJsonResponse(warehouseRepository.save(warehouse));
    }

    @Caching(evict = {
            @CacheEvict(value = "warehouses", key = "#warehouseId"),
            @CacheEvict(value = "warehouses", key = "'allWarehouses'")
    })
    public void deleteWarehouse(Long warehouseId) {
        Warehouse warehouse = retrieveWarehouse(warehouseId);
        warehouseRepository.delete(warehouse);
    }

    private WarehouseWithProductResponseJson getResponseWithProducts(Warehouse warehouse, Long usedCapacity) {
        return new WarehouseWithProductResponseJson()
                .setId(warehouse.getId())
                .setAddress(warehouse.getAddress())
                .setCapacity(warehouse.getCapacity())
                .setRemainingCapacity(warehouse.getCapacity() - usedCapacity)
                .setProducts(warehouse.getProductWarehouses()
                        .stream()
                        .map(this::getProductWarehouseJsonResponse)
                        .toList());

    }

    private WarehouseProductJsonResponse getProductWarehouseJsonResponse(ProductWarehouse productWarehouse) {
        Product product = productWarehouse.getProduct();
        return new WarehouseProductJsonResponse()
                .setId(product.getId())
                .setName(product.getName())
                .setQuantity(productWarehouse.getQuantity());
    }

    private Warehouse retrieveWarehouse(Long warehouseId) {
        return warehouseRepository.findById(warehouseId).orElseThrow(
                () -> {
                    logger.warn("Tried to retrieve invalid warehouse with id: {}", warehouseId);
                    return new CustomException(ErrorCode.ENTITY_NOT_FOUND.getCode(),
                            MessageFormat.format(ErrorCode.ENTITY_NOT_FOUND.getMessage(), WAREHOUSE_ENTITY, warehouseId));
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
