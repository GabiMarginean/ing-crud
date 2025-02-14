package com.ing.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
    private Set<ProductWarehouse> productWarehouses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Warehouse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Warehouse setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getCapacity() {
        return capacity;
    }

    public Warehouse setCapacity(Long capacity) {
        this.capacity = capacity;
        return this;
    }

    public Set<ProductWarehouse> getProductWarehouses() {
        return productWarehouses;
    }

    public Warehouse setProductWarehouses(Set<ProductWarehouse> productWarehouses) {
        this.productWarehouses = productWarehouses;
        return this;
    }
}
