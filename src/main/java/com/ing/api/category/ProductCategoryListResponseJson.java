package com.ing.api.category;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

public class ProductCategoryListResponseJson {

    @JsonUnwrapped
    private List<ProductCategoryResponseJson> categories;

    public List<ProductCategoryResponseJson> getCategories() {
        return categories;
    }

    public ProductCategoryListResponseJson setCategories(List<ProductCategoryResponseJson> categories) {
        this.categories = categories;
        return this;
    }
}
