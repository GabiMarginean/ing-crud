package com.ing.repository;

import com.ing.TestBase;
import com.ing.domain.Product;
import com.ing.domain.Warehouse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WarehouseRepositoryTest extends TestBase {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private WarehouseRepository warehouseRepository;

    private Product testProduct;
    private Warehouse testWarehouse;

    @BeforeEach
    public void setUp() {

        testWarehouse = new Warehouse()
                .setAddress(TEST_WAREHOUSE_ADDRESS)
                .setCapacity(TEST_WAREHOUSE_CAPACITY);

        testWarehouse = warehouseRepository.save(testWarehouse);

        testProduct = new Product()
                .setName(TEST_PRODUCT_NAME)
                .setDescription(TEST_PRODUCT_DESCRIPTION)
                .setPrice(TEST_PRODUCT_PRICE);

        testProduct = testEntityManager.persist(testProduct);
    }

    @AfterEach
    public void tearDown() {
        warehouseRepository.delete(testWarehouse);
        testEntityManager.remove(testProduct);
    }

    @Test
    public void testFindAll() {
        List<Warehouse> allWarehouses = warehouseRepository.findAll();

        assertThat(allWarehouses).hasSize(1);
        Warehouse w = allWarehouses.get(0);
        assertWarehouse(w);
        assertThat(w.getProductWarehouses()).hasSize(0);
    }

    @Test
    @Disabled
    public void testAddNewProduct() {
        testWarehouse.addProduct(testProduct, PRODUCT_WAREHOUSE_QUANTITY);
        warehouseRepository.save(testWarehouse);

        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(testWarehouse.getId());
        assertTrue(warehouseOptional.isPresent());
        Warehouse warehouse = warehouseOptional.get();
        assertThat(warehouse.getProductWarehouses()).hasSize(1);
        assertProduct(warehouse.getProductWarehouses().stream().toList().get(0).getProduct());
    }

    @Test
    public void testRemoveProduct() {
        testWarehouse.removeProduct(testProduct);
        warehouseRepository.save(testWarehouse);

        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(testWarehouse.getId());
        assertTrue(warehouseOptional.isPresent());
        assertThat(warehouseOptional.get().getProductWarehouses()).hasSize(0);
    }
}
