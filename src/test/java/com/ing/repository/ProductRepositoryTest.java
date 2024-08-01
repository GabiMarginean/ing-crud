package com.ing.repository;

import com.ing.domain.Product;
import com.ing.domain.ProductCategory;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ProductRepositoryTest extends TestBase {

    public static final String PRODUCT_UPDATED_NAME = "new name";

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProductRepository productRepository;

    private Product testProduct;
    private Warehouse testWarehouse;

    @BeforeEach
    public void setUp() {
        testProduct = new Product()
                .setName(TEST_PRODUCT_NAME)
                .setDescription(TEST_PRODUCT_DESCRIPTION)
                .setPrice(TEST_PRODUCT_PRICE);

        testWarehouse = new Warehouse()
                .setAddress(TEST_WAREHOUSE_ADDRESS)
                .setCapacity(TEST_WAREHOUSE_CAPACITY);

        testWarehouse = testEntityManager.persist(testWarehouse);
        testProduct = productRepository.save(testProduct);
    }

    @AfterEach
    public void tearDown() {
        productRepository.delete(testProduct);
        testEntityManager.remove(testWarehouse);
    }

    @Test
    public void testFindAll() {
        List<Product> allProducts = productRepository.findAll();
        assertThat(allProducts).hasSize(1);
        assertProduct(allProducts.get(0));
    }

    @Test
    public void testUpdate() {
        Product testProduct = getTestProduct();
        testProduct.setName(PRODUCT_UPDATED_NAME);
        productRepository.save(testProduct);
        assertProduct(PRODUCT_UPDATED_NAME);
    }

    @Test
    public void testAssignCategory() {
        Product testProduct = getTestProduct();
        ProductCategory category = new ProductCategory().setName(TEST_CATEGORY_NAME);
        testProduct.setCategory(category);
        productRepository.save(testProduct);
        assertProduct(true, false);
    }

    @Test
    public void testAssignWarehouse() {
        Product testProduct = getTestProduct();

        testProduct.assignToWarehouse(testWarehouse, PRODUCT_WAREHOUSE_QUANTITY);
        productRepository.save(testProduct);

        assertProduct(false, true);
    }

    @Test
    public void testRemoveProduct() {
        productRepository.deleteById(testProduct.getId());
        List<Product> products = productRepository.findAll();
        assertEquals(0, products.size());
    }

    private Product getTestProduct() {
        Optional<Product> productOptional = productRepository.findById(testProduct.getId());
        assertTrue(productOptional.isPresent());
        return productOptional.get();
    }

    private void assertProduct(boolean withCategory, boolean withWarehouse) {
        Product savedProduct = getTestProduct();
        assertProduct(savedProduct, TEST_PRODUCT_NAME, withCategory, withWarehouse);
    }

    private void assertProduct(String productName) {
        Product savedProduct = getTestProduct();
        assertProduct(savedProduct, productName, false, false);
    }


}
