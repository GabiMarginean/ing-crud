package com.ing.controller;

import com.ing.api.ProductListResponse;
import com.ing.api.ProductRequestJson;
import com.ing.api.ProductResponseJson;
import com.ing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ProductListResponse getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductResponseJson getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @PostMapping
    public ProductResponseJson createProduct(@RequestBody ProductRequestJson productJson) {
        return productService.createProduct(productJson);
    }

    @PutMapping("/{productId}")
    public ProductResponseJson updateProduct(@PathVariable Long productId, @RequestBody ProductRequestJson productJson) {
        return productService.updateProduct(productJson, productId);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
