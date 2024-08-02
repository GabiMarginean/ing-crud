package com.ing.service;

import com.ing.api.category.ProductCategoryListResponseJson;
import com.ing.api.category.ProductCategoryResponseJson;
import com.ing.domain.ProductCategory;
import com.ing.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoryListResponseJson getCategories() {
        return getListResponse(productCategoryRepository.findAll());
    }

    public ProductCategoryListResponseJson getListResponse(List<ProductCategory> categories) {
        return new ProductCategoryListResponseJson()
                .setCategories(categories.stream()
                        .map(this::toJsonResponse)
                        .toList());
    }

    public ProductCategoryResponseJson toJsonResponse(ProductCategory category) {
        return new ProductCategoryResponseJson()
                .setId(category.getId())
                .setName(category.getName());
    }
}
