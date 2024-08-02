package com.ing.controller;

import com.ing.TestBase;
import com.ing.api.ProductListResponse;
import com.ing.api.ProductResponseJson;
import com.ing.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.ing.service.ProductServiceTest.TEST_PRODUCT_ID;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
@WithMockUser
public class ProductControllerTest extends TestBase {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductResponseJson testProductResponseJson;
    private ProductListResponse testProductListResponse;

    @BeforeEach
    public void setup() {
        testProductResponseJson = new ProductResponseJson()
                .setId(TEST_PRODUCT_ID)
                .setName(TEST_PRODUCT_NAME)
                .setDescription(TEST_PRODUCT_DESCRIPTION)
                .setPrice(TEST_PRODUCT_PRICE)
                .setCategory(TEST_CATEGORY_NAME);

        testProductListResponse = new ProductListResponse().setProducts(List.of(testProductResponseJson));

        when(productService.getAllProducts()).thenReturn(testProductListResponse);
        when(productService.getProduct(TEST_PRODUCT_ID)).thenReturn(testProductResponseJson);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.products[0].id").value(TEST_PRODUCT_ID))
                .andExpect(jsonPath("$.products[0].name").value(TEST_PRODUCT_NAME));
    }

    @Test
    public void testGetProduct() throws Exception {
        mockMvc.perform(get("/api/products/{productId}", TEST_PRODUCT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(TEST_PRODUCT_ID))
                .andExpect(jsonPath("$.name").value(TEST_PRODUCT_NAME));
    }

}
