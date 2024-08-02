package com.ing.controller;

import com.ing.api.category.ProductCategoryListResponseJson;
import com.ing.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    public ProductCategoryListResponseJson getAllProducts() {
        return productCategoryService.getCategories();
    }
}
