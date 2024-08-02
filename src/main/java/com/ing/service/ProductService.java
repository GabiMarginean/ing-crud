package com.ing.service;

import com.ing.api.product.*;
import com.ing.domain.Product;
import com.ing.domain.ProductCategory;
import com.ing.domain.ProductWarehouse;
import com.ing.domain.Warehouse;
import com.ing.error.CustomException;
import com.ing.error.ErrorCode;
import com.ing.repository.ProductCategoryRepository;
import com.ing.repository.ProductRepository;
import com.ing.repository.WarehouseRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class ProductService {

    public static final String WAREHOUSE_ENTITY = "Warehouse";
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    public static final String PRODUCT_ENTITY = "Product";

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductListResponseJson getAllProducts() {
        List<Product> allProducts = productRepository.findAll();

        return getProductListResponse(allProducts);
    }

    public ProductResponseJson getProduct(Long productId) {
        Product product = retrieveProduct(productId);

        return toJsonResponse(product);
    }

    public ProductResponseJson createProduct(ProductRequestJson productJson) {
        ProductCategory productCategory = retrieveProductCategory(productJson);
        Product product = toEntity(productJson).setCategory(productCategory);

        return toJsonResponse(productRepository.save(product));
    }

    public ProductResponseJson updateProduct(ProductRequestJson productJson, Long productId) {
        retrieveProduct(productId);
        ProductCategory productCategory = retrieveProductCategory(productJson);
        Product product = toEntity(productJson).setCategory(productCategory)
                .setId(productId);

        return toJsonResponse(productRepository.save(product));
    }

    public void deleteProduct(Long productId) {
        Product product = retrieveProduct(productId);
        productRepository.delete(product);
    }

    public ProductResponseJson updateProductPrice(Long productId, PriceUpdateRequestJson priceUpdateRequest) {
        Product product = retrieveProduct(productId);
        product.setPrice(priceUpdateRequest.getPrice());

        productRepository.save(product);

        return toJsonResponse(product);
    }

    @Transactional
    public ProductAssignWarehouseResponseJson assignProductToWarehouse(Long productId, ProductAssignWarehouseJsonListRequest assignRequest) {
        Product product = retrieveProduct(productId);
        List<ProductAssignWarehouseJsonRequest> assignRequests = assignRequest.getAssignRequests();
        assignRequests.forEach(request -> processAssignToWarehouse(product, request));

        return toJsonWithWarehousesResponse(productRepository.save(product));
    }

    private void processAssignToWarehouse(Product product, ProductAssignWarehouseJsonRequest request) {
        Warehouse warehouse = retrieveWarehouse(request.getWarehouseId());
        Long usedStorage = warehouseRepository.countProductsInWarehouse(request.getWarehouseId());
        Long remainingStorage = warehouse.getCapacity() - usedStorage;

        if (remainingStorage < request.getQuantity()) {
            logger.warn("Could not assign product {} to warehouse {}: Exceeded capacity actual {}, required {}",
                    product.getId(), request.getWarehouseId(), remainingStorage, request.getQuantity());
            throw new CustomException(ErrorCode.WAREHOUSE_NOT_ENOUGH_STORAGE.getCode(),
                    MessageFormat.format(ErrorCode.WAREHOUSE_NOT_ENOUGH_STORAGE.getMessage(), request.getWarehouseId(), request.getQuantity(), remainingStorage));
        }

        product.assignToWarehouse(warehouse, request.getQuantity());
    }

    private Product retrieveProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> {
                    logger.warn("Tried to retrieve invalid product with id: {}", productId);
                    return new CustomException(ErrorCode.ENTITY_NOT_FOUND.getCode(),
                            MessageFormat.format(ErrorCode.ENTITY_NOT_FOUND.getMessage(), PRODUCT_ENTITY, productId));
                });
    }

    private Warehouse retrieveWarehouse(Long productId) {
        return warehouseRepository.findById(productId).orElseThrow(
                () -> {
                    logger.warn("Tried to retrieve invalid warehouse with id: {}", productId);
                    return new CustomException(ErrorCode.ENTITY_NOT_FOUND.getCode(),
                            MessageFormat.format(ErrorCode.ENTITY_NOT_FOUND.getMessage(), WAREHOUSE_ENTITY, productId));
                });
    }

    private ProductCategory retrieveProductCategory(ProductRequestJson productJson) {
        return productCategoryRepository.findByName(productJson.getCategory()).orElseThrow(
                () -> {
                    logger.warn("Tried to retrieve invalid product category: {}", productJson.getCategory());
                    return new CustomException(ErrorCode.INVALID_CATEGORY.getCode(),
                            MessageFormat.format(ErrorCode.INVALID_CATEGORY.getMessage(), productJson.getCategory()));
                });
    }

    private ProductResponseJson toJsonResponse(Product product) {
        return new ProductResponseJson()
                .setId(product.getId())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setPrice(product.getPrice())
                .setCategory(product.getCategory().getName());
    }

    private ProductAssignWarehouseResponseJson toJsonWithWarehousesResponse(Product product) {
        return new ProductAssignWarehouseResponseJson()
                .setId(product.getId())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setPrice(product.getPrice())
                .setCategory(product.getCategory().getName())
                .setWarehouses(product.getProductWarehouses()
                        .stream()
                        .map(this::toProductWarehouseResponseJson)
                        .toList());
    }

    private ProductWarehouseResponseJson toProductWarehouseResponseJson(ProductWarehouse productWarehouse) {
        return new ProductWarehouseResponseJson()
                .setWarehouseId(productWarehouse.getWarehouse().getId())
                .setQuantity(productWarehouse.getQuantity());
    }

    private ProductListResponseJson getProductListResponse(List<Product> allProducts) {
        return new ProductListResponseJson()
                .setProducts(allProducts.stream()
                        .map(this::toJsonResponse)
                        .toList());
    }

    private Product toEntity(ProductRequestJson product) {
        return new Product()
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setPrice(product.getPrice());
    }

}
