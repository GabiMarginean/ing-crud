package com.ing.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "product_warehouse")
public class ProductWarehouse {

    @EmbeddedId
    private ProductWarehouseId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("warehouseId")
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    public ProductWarehouse(Product product, Warehouse warehouse, long quantity) {
        this.product = product;
        this.warehouse = warehouse;
        this.id = new ProductWarehouseId(product.getId(), warehouse.getId());
        this.quantity = quantity;
    }

    public ProductWarehouse() {
    }

    public ProductWarehouseId getId() {
        return id;
    }

    public ProductWarehouse setId(ProductWarehouseId id) {
        this.id = id;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public ProductWarehouse setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public ProductWarehouse setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public ProductWarehouse setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWarehouse that = (ProductWarehouse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
