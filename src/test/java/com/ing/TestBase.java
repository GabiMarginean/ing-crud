package com.ing;

import com.ing.api.product.ProductRequestJson;
import com.ing.domain.Product;
import com.ing.domain.ProductWarehouse;
import com.ing.domain.Warehouse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBase {

    protected static final String TEST_CATEGORY_NAME = "category";

    protected static final String TEST_WAREHOUSE_ADDRESS = "address";
    protected static final long TEST_WAREHOUSE_CAPACITY = 100L;

    protected static final String TEST_PRODUCT_NAME = "name";
    protected static final String TEST_PRODUCT_DESCRIPTION = "desc";
    protected static final double TEST_PRODUCT_PRICE = 12D;

    public static final long PRODUCT_WAREHOUSE_QUANTITY = 10L;

    protected void assertProduct(Product p) {
        assertProduct(p, TEST_PRODUCT_NAME, false, false);
    }

    protected void assertProduct(Product p, String name, boolean withCategory, boolean withWarehouse) {
        assertEquals(name, p.getName());
        assertEquals(TEST_PRODUCT_DESCRIPTION, p.getDescription());
        assertEquals(TEST_PRODUCT_PRICE, p.getPrice());

        if (withCategory) {
            assertEquals(TEST_CATEGORY_NAME, p.getCategory().getName());
        }

        if (withWarehouse) {
            assertEquals(1, p.getProductWarehouses().size());
            ProductWarehouse productWarehouse = p.getProductWarehouses().stream().findFirst().get();
            assertEquals(PRODUCT_WAREHOUSE_QUANTITY, productWarehouse.getQuantity());
            assertWarehouse(productWarehouse.getWarehouse());
        }
    }

    protected void assertWarehouse(Warehouse w) {
        assertEquals(TEST_WAREHOUSE_ADDRESS, w.getAddress());
        assertEquals(TEST_WAREHOUSE_CAPACITY, w.getCapacity());
    }

    protected ProductRequestJson getRequestJson() {
        return getRequestJson(TEST_CATEGORY_NAME);
    }

    protected ProductRequestJson getRequestJson(String categoryName) {
        return new ProductRequestJson().setName(TEST_PRODUCT_NAME)
                .setDescription(TEST_PRODUCT_DESCRIPTION)
                .setPrice(TEST_PRODUCT_PRICE)
                .setCategory(categoryName);

    }
}
