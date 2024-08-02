package com.ing.repository;

import com.ing.TestBase;
import com.ing.domain.Product;
import com.ing.domain.Warehouse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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
    }

    @AfterEach
    public void tearDown() {
        warehouseRepository.delete(testWarehouse);
    }

    @Test
    public void testFindAll() {
        List<Warehouse> allWarehouses = warehouseRepository.findAll();

        assertThat(allWarehouses).hasSize(1);
        Warehouse w = allWarehouses.get(0);
        assertWarehouse(w);
        assertThat(w.getProductWarehouses()).hasSize(0);
    }
}
