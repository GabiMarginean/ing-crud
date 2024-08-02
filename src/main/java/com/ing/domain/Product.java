package com.ing.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private ProductCategory category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductWarehouse> productWarehouses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public Product setCategory(ProductCategory category) {
        this.category = category;
        return this;
    }

    public Set<ProductWarehouse> getProductWarehouses() {
        return productWarehouses;
    }

    public Product setProductWarehouses(Set<ProductWarehouse> productWarehouses) {
        this.productWarehouses = productWarehouses;
        return this;
    }

    public Product assignToWarehouse(Warehouse warehouse, long quantity) {
        // Check if the relationship already exists
        for (ProductWarehouse productWarehouse : productWarehouses) {
            if (productWarehouse.getProduct().equals(this) && productWarehouse.getWarehouse().equals(warehouse)) {
                // Update the quantity if it already exists
                productWarehouse.setQuantity(quantity);
                return this;
            }
        }
        // If not, create a new relationship
        ProductWarehouse productWarehouse = new ProductWarehouse(this, warehouse, quantity);
        productWarehouses.add(productWarehouse);
        warehouse.getProductWarehouses().add(productWarehouse);
        return this;
    }


    public Product removeFromWarehouse(Warehouse warehouse) {
        for (Iterator<ProductWarehouse> iterator = productWarehouses.iterator(); iterator.hasNext(); ) {
            ProductWarehouse productWarehouse = iterator.next();

            if (productWarehouse.getProduct().equals(this) &&
                    productWarehouse.getWarehouse().equals(warehouse)) {
                iterator.remove();
                productWarehouse.getWarehouse().getProductWarehouses().remove(productWarehouse);
                productWarehouse.setProduct(null);
                productWarehouse.setWarehouse(null);
            }
        }
        return this;
    }


}
