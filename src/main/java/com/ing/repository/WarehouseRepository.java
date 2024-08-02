package com.ing.repository;

import com.ing.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query("select w from Warehouse w left join fetch w.productWarehouses p where w.id = :id")
    Warehouse findWithProductsById(@Param("id") Long id);

    @Query("select coalesce(sum(p.quantity), 0) from Warehouse w " +
            "left join w.productWarehouses p where w.id = :id")
    Long countProductsInWarehouse(@Param("id") Long id);
}
