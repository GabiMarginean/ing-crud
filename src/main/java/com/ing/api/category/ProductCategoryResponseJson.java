package com.ing.api.category;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ProductCategoryResponseJson implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public Long getId() {
        return id;
    }

    public ProductCategoryResponseJson setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductCategoryResponseJson setName(String name) {
        this.name = name;
        return this;
    }
}
