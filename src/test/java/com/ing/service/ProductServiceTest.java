package com.ing.service;

import com.ing.TestBase;
import com.ing.api.product.ProductListResponseJson;
import com.ing.api.product.ProductResponseJson;
import com.ing.domain.Product;
import com.ing.domain.ProductCategory;
import com.ing.error.ErrorCode;
import com.ing.repository.ProductCategoryRepository;
import com.ing.repository.ProductRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest extends TestBase {

    public static final long TEST_PRODUCT_ID = 1L;
    public static final long TEST_PRODUCT_CATEGORY_ID = 1L;

    public static final ProductCategory TEST_CATEGORY = new ProductCategory()
            .setId(TEST_PRODUCT_CATEGORY_ID)
            .setName(TEST_CATEGORY_NAME);

    public static final Product TEST_PRODUCT = new Product()
            .setId(TEST_PRODUCT_ID)
            .setName(TEST_PRODUCT_NAME)
            .setDescription(TEST_PRODUCT_DESCRIPTION)
            .setPrice(TEST_PRODUCT_PRICE)
            .setCategory(TEST_CATEGORY);

    public static final long INVALID_PRODUCT_ID = 2L;
    public static final String INVALID_CATEGORY_NAME = "invalidCategory";
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductCategoryRepository productCategoryRepository;
    @InjectMocks
    private ProductService productService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        when(productRepository.findAll()).thenReturn(List.of(TEST_PRODUCT));
        when(productRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(TEST_PRODUCT));
        when(productRepository.save(any())).thenReturn(TEST_PRODUCT);
        when(productCategoryRepository.findByName(TEST_CATEGORY_NAME)).thenReturn(Optional.of(TEST_CATEGORY));
    }

    @Test
    public void testGetAllProducts() {
        ProductListResponseJson allProducts = productService.getAllProducts();

        assertNotNull(allProducts);
        assertThat(allProducts.getProducts()).hasSize(1);
        assertProductJson(allProducts.getProducts().get(0));
    }

    @Test
    public void testGetOneProduct() {
        ProductResponseJson product = productService.getProduct(TEST_PRODUCT_ID);

        assertNotNull(product);
        assertProductJson(product);
    }

    @Test
    public void testGetOneProductInvalidId() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage(MessageFormat.format(ErrorCode.ENTITY_NOT_FOUND.getMessage(), INVALID_PRODUCT_ID));

        ProductResponseJson product = productService.getProduct(INVALID_PRODUCT_ID);

        assertNotNull(product);
        assertProductJson(product);
    }

    @Test
    public void testCreateProduct() {
        ProductResponseJson product = productService.createProduct(getRequestJson());

        assertNotNull(product);
        assertProductJson(product);
    }

    @Test
    public void testCreateProductInvalidCategory() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage(MessageFormat.format(ErrorCode.INVALID_CATEGORY.getMessage(), INVALID_CATEGORY_NAME));

        ProductResponseJson product = productService.createProduct(getRequestJson(INVALID_CATEGORY_NAME));
    }

    @Test
    public void testUpdateProduct() {
        ProductResponseJson product = productService.updateProduct(getRequestJson(), TEST_PRODUCT_ID);

        assertNotNull(product);
        assertProductJson(product);
    }

    @Test
    public void testUpdateProductInvalidId() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage(MessageFormat.format(ErrorCode.ENTITY_NOT_FOUND.getMessage(), INVALID_PRODUCT_ID));

        ProductResponseJson product = productService.updateProduct(getRequestJson(), INVALID_PRODUCT_ID);
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(TEST_PRODUCT_ID);

        verify(productRepository).delete(TEST_PRODUCT);
    }

    @Test
    public void testDeleteProductInvalidId() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage(MessageFormat.format(ErrorCode.ENTITY_NOT_FOUND.getMessage(), INVALID_PRODUCT_ID));

        productService.deleteProduct(INVALID_PRODUCT_ID);

        verify(productRepository, times(0)).delete(TEST_PRODUCT);
    }

    private void assertProductJson(ProductResponseJson responseJson) {
        assertEquals(TEST_PRODUCT_ID, responseJson.getId().longValue());
        assertEquals(TEST_PRODUCT_NAME, responseJson.getName());
        assertEquals(TEST_PRODUCT_DESCRIPTION, responseJson.getDescription());
        assertEquals(TEST_PRODUCT_PRICE, responseJson.getPrice(), 0.0);
        assertEquals(TEST_CATEGORY_NAME, responseJson.getCategory());
    }
}
