package com.ing.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductWarehouseId implements Serializable {

    private Long productId;
    private Long warehouseId;

    public Long getProductId() {
        return productId;
    }

    public ProductWarehouseId setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public ProductWarehouseId setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
        return this;
    }

    public ProductWarehouseId() {
    }

    public ProductWarehouseId(Long productId, Long warehouseId) {
        this.productId = productId;
        this.warehouseId = warehouseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWarehouseId that = (ProductWarehouseId) o;
        return Objects.equals(productId, that.productId)
                && Objects.equals(warehouseId, that.warehouseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, warehouseId);
    }
}
