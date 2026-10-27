package com.inventoryservice.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoryservice.application.usecase.ProductUseCase;
import com.inventoryservice.domain.model.Product;
import com.inventoryservice.infrastructure.controller.dto.ProductRequest;
import com.inventoryservice.infrastructure.controller.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@DisplayName("ProductController - Tests de Integración")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductUseCase productUseCase;

    private Product testProduct;
    private UUID testProductId;
    private ProductRequest productRequest;
    private ProductResponse productResponse;

    @BeforeEach
    void setUp() {
        testProductId = UUID.randomUUID();
        testProduct = new Product(
            testProductId,
            "SKU-TEST-001",
            "Producto de Test",
            "Descripción del producto de test",
            new BigDecimal("99.99"),
            50,
            LocalDateTime.now(),
            LocalDateTime.now(),
            "ELECTRONICS"
        );

        productRequest = new ProductRequest(
            "SKU-TEST-001",
            "Producto de Test",
            "Descripción del producto de test",
            new BigDecimal("99.99"),
            50,
            "ELECTRONICS"
        );

        productResponse = new ProductResponse(
            testProductId,
            "SKU-TEST-001",
            "Producto de Test",
            "Descripción del producto de test",
            new BigDecimal("99.99"),
            50,
            LocalDateTime.now(),
            LocalDateTime.now(),
            "ELECTRONICS"
        );
    }

    @Nested
    @DisplayName("POST /api/products - Crear Producto")
    class CreateProduct {

        @Test
        @DisplayName("Debería crear un producto y retornar 201 Created")
        void shouldCreateProductAndReturn201() throws Exception {
            when(productUseCase.createProduct(any(Product.class))).thenReturn(testProduct);

            mockMvc.perform(post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(testProductId.toString()))
                .andExpect(jsonPath("$.sku").value("SKU-TEST-001"));
        }

        @Test
        @DisplayName("Debería retornar 400 Bad Request cuando el SKU está vacío")
        void shouldReturn400WhenSkuIsEmpty() throws Exception {
            ProductRequest invalidRequest = new ProductRequest(
                "",
                "Producto",
                "Descripción",
                new BigDecimal("99.99"),
                50,
                "ELECTRONICS"
            );

            mockMvc.perform(post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debería retornar 400 Bad Request cuando el precio es negativo")
        void shouldReturn400WhenPriceIsNegative() throws Exception {
            ProductRequest invalidRequest = new ProductRequest(
                "SKU-001",
                "Producto",
                "Descripción",
                new BigDecimal("-10.00"),
                50,
                "ELECTRONICS"
            );

            mockMvc.perform(post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debería retornar 409 Conflict cuando el SKU ya existe")
        void shouldReturn409WhenSkuExists() throws Exception {
            when(productUseCase.createProduct(any(Product.class)))
                .thenThrow(new IllegalArgumentException("El SKU ya existe"));

            mockMvc.perform(post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isConflict());
        }
    }

    @Nested
    @DisplayName("GET /api/products - Obtener Todos los Productos")
    class GetAllProducts {

        @Test
        @DisplayName("Debería obtener todos los productos y retornar 200 OK")
        void shouldGetAllProductsAndReturn200() throws Exception {
            List<Product> productList = new ArrayList<>();
            productList.add(testProduct);
            productList.add(new Product(
                UUID.randomUUID(),
                "SKU-TEST-002",
                "Segundo Producto",
                "Descripción dos",
                new BigDecimal("199.99"),
                30,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "HOME"
            ));

            when(productUseCase.getAllProducts()).thenReturn(productList);

            mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].sku").value("SKU-TEST-001"))
                .andExpect(jsonPath("$[1].sku").value("SKU-TEST-002"));
        }

        @Test
        @DisplayName("Debería retornar lista vacía cuando no hay productos")
        void shouldReturnEmptyListWhenNoProducts() throws Exception {
            when(productUseCase.getAllProducts()).thenReturn(new ArrayList<>());

            mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
        }
    }

    @Nested
    @DisplayName("GET /api/products/{id} - Obtener Producto por ID")
    class GetProductById {

        @Test
        @DisplayName("Debería obtener un producto por su ID y retornar 200 OK")
        void shouldGetProductByIdAndReturn200() throws Exception {
            when(productUseCase.getProductById(testProductId)).thenReturn(testProduct);

            mockMvc.perform(get("/api/products/{id}", testProductId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testProductId.toString()))
                .andExpect(jsonPath("$.sku").value("SKU-TEST-001"))
                .andExpect(jsonPath("$.name").value("Producto de Test"));
        }

        @Test
        @DisplayName("Debería retornar 404 Not Found cuando el producto no existe")
        void shouldReturn404WhenProductNotFound() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(productUseCase.getProductById(nonExistentId))
                .thenThrow(new IllegalArgumentException("Producto no encontrado"));

            mockMvc.perform(get("/api/products/{id}", nonExistentId))
                .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Debería retornar 400 Bad Request cuando el ID no es válido")
        void shouldReturn400WhenIdIsInvalid() throws Exception {
            mockMvc.perform(get("/api/products/{id}", "invalid-uuid"))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("PUT /api/products/{id} - Actualizar Producto")
    class UpdateProduct {

        @Test
        @DisplayName("Debería actualizar un producto y retornar 200 OK")
        void shouldUpdateProductAndReturn200() throws Exception {
            Product updatedProduct = new Product(
                testProductId,
                "SKU-TEST-001",
                "Producto Actualizado",
                "Nueva descripción",
                new BigDecimal("149.99"),
                100,
                testProduct.getCreatedAt(),
                LocalDateTime.now(),
                "ELECTRONICS"
            );

            when(productUseCase.updateProduct(eq(testProductId), any(Product.class)))
                .thenReturn(updatedProduct);

            ProductRequest updateRequest = new ProductRequest(
                "SKU-TEST-001",
                "Producto Actualizado",
                "Nueva descripción",
                new BigDecimal("149.99"),
                100,
                "ELECTRONICS"
            );

            mockMvc.perform(put("/api/products/{id}", testProductId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Producto Actualizado"))
                .andExpect(jsonPath("$.price").value(149.99));
        }

        @Test
        @DisplayName("Debería retornar 404 Not Found al actualizar un producto inexistente")
        void shouldReturn404WhenUpdatingNonExistentProduct() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(productUseCase.updateProduct(eq(nonExistentId), any(Product.class)))
                .thenThrow(new IllegalArgumentException("Producto no encontrado"));

            mockMvc.perform(put("/api/products/{id}", nonExistentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("DELETE /api/products/{id} - Eliminar Producto")
    class DeleteProduct {

        @Test
        @DisplayName("Debería eliminar un producto y retornar 204 No Content")
        void shouldDeleteProductAndReturn204() throws Exception {
            doNothing().when(productUseCase).deleteProduct(testProductId);

            mockMvc.perform(delete("/api/products/{id}", testProductId))
                .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("Debería retornar 404 Not Found al eliminar un producto inexistente")
        void shouldReturn404WhenDeletingNonExistentProduct() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            doThrow(new IllegalArgumentException("Producto no encontrado"))
                .when(productUseCase).deleteProduct(nonExistentId);

            mockMvc.perform(delete("/api/products/{id}", nonExistentId))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("PATCH /api/products/{id}/reduce-stock - Reducir Stock")
    class ReduceStock {

        @Test
        @DisplayName("Debería reducir el stock y retornar 200 OK")
        void shouldReduceStockAndReturn200() throws Exception {
            Product reducedProduct = new Product(
                testProductId,
                "SKU-TEST-001",
                "Producto de Test",
                "Descripción del producto de test",
                new BigDecimal("99.99"),
                40,
                testProduct.getCreatedAt(),
                LocalDateTime.now(),
                "ELECTRONICS"
            );

            when(productUseCase.reduceStock(testProductId, 10)).thenReturn(reducedProduct);

            mockMvc.perform(patch("/api/products/{id}/reduce-stock", testProductId)
                    .param("quantity", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stockQuantity").value(40));
        }

        @Test
        @DisplayName("Debería retornar 400 Bad Request cuando la cantidad es negativa")
        void shouldReturn400WhenQuantityIsNegative() throws Exception {
            mockMvc.perform(patch("/api/products/{id}/reduce-stock", testProductId)
                    .param("quantity", "-5"))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debería retornar 409 Conflict cuando el stock es insuficiente")
        void shouldReturn409WhenInsufficientStock() throws Exception {
            when(productUseCase.reduceStock(testProductId, 100))
                .thenThrow(new IllegalStateException("Stock insuficiente"));

            mockMvc.perform(patch("/api/products/{id}/reduce-stock", testProductId)
                    .param("quantity", "100"))
                .andExpect(status().isConflict());
        }

        @Test
        @DisplayName("Debería retornar 404 Not Found cuando el producto no existe")
        void shouldReturn404WhenProductNotFound() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(productUseCase.reduceStock(nonExistentId, 10))
                .thenThrow(new IllegalArgumentException("Producto no encontrado"));

            mockMvc.perform(patch("/api/products/{id}/reduce-stock", nonExistentId)
                    .param("quantity", "10"))
                .andExpect(status().isNotFound());
        }
    }
}