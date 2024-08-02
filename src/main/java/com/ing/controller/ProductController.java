package com.ing.controller;

import com.ing.api.product.*;
import com.ing.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ProductListResponseJson getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductResponseJson getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @PostMapping
    public ProductResponseJson createProduct(@RequestBody @Valid ProductRequestJson productJson) {
        return productService.createProduct(productJson);
    }

    @PutMapping("/{productId}")
    public ProductResponseJson updateProduct(@PathVariable Long productId,
                                             @RequestBody @Valid ProductRequestJson productJson) {
        return productService.updateProduct(productJson, productId);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{productId}/price")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseJson updateProductPrice(@PathVariable Long productId,
                                                  @RequestBody @Valid PriceUpdateRequestJson priceUpdateRequest) {
        return productService.updateProductPrice(productId, priceUpdateRequest);
    }

    @PostMapping("/{productId}/assign-warehouse")
    public ProductAssignWarehouseResponseJson assignToWarehouse(@PathVariable Long productId,
                                                                @RequestBody @Valid ProductAssignWarehouseJsonListRequest assignRequest) {
        return productService.assignProductToWarehouse(productId, assignRequest);
    }
}
