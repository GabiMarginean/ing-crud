package com.ing.service;

import com.ing.api.product.PriceUpdateRequestJson;
import com.ing.api.product.ProductListResponseJson;
import com.ing.api.product.ProductRequestJson;
import com.ing.api.product.ProductResponseJson;
import com.ing.domain.Product;
import com.ing.domain.ProductCategory;
import com.ing.error.ErrorCode;
import com.ing.repository.ProductCategoryRepository;
import com.ing.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

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

        // Convert to response JSON (assuming you have a method for this)
        return toJsonResponse(product);
    }

    private Product retrieveProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> {
                    logger.warn("Tried to retrieve invalid product with id: {}", productId);
                    return new RuntimeException(MessageFormat.format(ErrorCode.ENTITY_NOT_FOUND.getMessage(), PRODUCT_ENTITY, productId));
                });
    }

    private ProductCategory retrieveProductCategory(ProductRequestJson productJson) {
        return productCategoryRepository.findByName(productJson.getCategory()).orElseThrow(
                () -> {
                    logger.warn("Tried to retrieve invalid product category: {}", productJson.getCategory());
                    return new RuntimeException(MessageFormat.format(ErrorCode.INVALID_CATEGORY.getMessage(), productJson.getCategory()));
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
