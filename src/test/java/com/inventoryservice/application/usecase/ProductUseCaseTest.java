package com.inventoryservice.application.usecase;

import com.inventoryservice.domain.model.Product;
import com.inventoryservice.domain.port.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductUseCase - Tests Unitarios")
class ProductUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductUseCase productUseCase;

    private Product testProduct;
    private UUID testProductId;

    @BeforeEach
    void setUp() {
        testProductId = UUID.randomUUID();
        testProduct = new Product(
            testProductId,
            "SKU-001",
            "Producto de Prueba",
            "Descripción del producto de prueba",
            new BigDecimal("99.99"),
            50,
            LocalDateTime.now(),
            LocalDateTime.now(),
            "ELECTRONICS"
        );
    }

    @Nested
    @DisplayName("Crear Producto")
    class CreateProduct {

        @Test
        @DisplayName("Debería crear un producto exitosamente cuando los datos son válidos")
        void shouldCreateProductSuccessfully() {
            when(productRepository.existsBySku(testProduct.getSku())).thenReturn(false);
            when(productRepository.save(any(Product.class))).thenReturn(testProduct);

            Product result = productUseCase.createProduct(testProduct);

            assertNotNull(result);
            assertEquals(testProduct.getId(), result.getId());
            assertEquals(testProduct.getSku(), result.getSku());
            verify(productRepository).existsBySku(testProduct.getSku());
            verify(productRepository).save(any(Product.class));
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el SKU ya existe")
        void shouldThrowExceptionWhenSkuExists() {
            when(productRepository.existsBySku(testProduct.getSku())).thenReturn(true);

            assertThrows(IllegalArgumentException.class, () -> {
                productUseCase.createProduct(testProduct);
            });

            verify(productRepository, never()).save(any(Product.class));
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el producto es nulo")
        void shouldThrowExceptionWhenProductIsNull() {
            assertThrows(NullPointerException.class, () -> {
                productUseCase.createProduct(null);
            });
        }
    }

    @Nested
    @DisplayName("Obtener Producto por ID")
    class GetProductById {

        @Test
        @DisplayName("Debería obtener un producto por su ID")
        void shouldGetProductById() {
            when(productRepository.findById(testProductId)).thenReturn(Optional.of(testProduct));

            Product result = productUseCase.getProductById(testProductId);

            assertNotNull(result);
            assertEquals(testProductId, result.getId());
            verify(productRepository).findById(testProductId);
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el producto no existe")
        void shouldThrowExceptionWhenProductNotFound() {
            UUID nonExistentId = UUID.randomUUID();
            when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            assertThrows(IllegalArgumentException.class, () -> {
                productUseCase.getProductById(nonExistentId);
            });
        }
    }

    @Nested
    @DisplayName("Actualizar Producto")
    class UpdateProduct {

        @Test
        @DisplayName("Debería actualizar un producto exitosamente")
        void shouldUpdateProductSuccessfully() {
            Product updatedProduct = new Product(
                testProductId,
                "SKU-001",
                "Producto Actualizado",
                "Nueva descripción",
                new BigDecimal("149.99"),
                100,
                testProduct.getCreatedAt(),
                LocalDateTime.now(),
                "ELECTRONICS"
            );

            when(productRepository.findById(testProductId)).thenReturn(Optional.of(testProduct));
            when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

            Product result = productUseCase.updateProduct(testProductId, updatedProduct);

            assertNotNull(result);
            assertEquals("Producto Actualizado", result.getName());
            verify(productRepository).findById(testProductId);
            verify(productRepository).save(any(Product.class));
        }

        @Test
        @DisplayName("Debería lanzar excepción al actualizar un producto inexistente")
        void shouldThrowExceptionWhenUpdatingNonExistentProduct() {
            UUID nonExistentId = UUID.randomUUID();
            when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            assertThrows(IllegalArgumentException.class, () -> {
                productUseCase.updateProduct(nonExistentId, testProduct);
            });
        }
    }

    @Nested
    @DisplayName("Eliminar Producto")
    class DeleteProduct {

        @Test
        @DisplayName("Debería eliminar un producto exitosamente")
        void shouldDeleteProductSuccessfully() {
            when(productRepository.findById(testProductId)).thenReturn(Optional.of(testProduct));
            when(productRepository.deleteById(testProductId)).thenReturn(true);

            assertDoesNotThrow(() -> {
                productUseCase.deleteProduct(testProductId);
            });

            verify(productRepository).findById(testProductId);
            verify(productRepository).deleteById(testProductId);
        }

        @Test
        @DisplayName("Debería lanzar excepción al eliminar un producto inexistente")
        void shouldThrowExceptionWhenDeletingNonExistentProduct() {
            UUID nonExistentId = UUID.randomUUID();
            when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            assertThrows(IllegalArgumentException.class, () -> {
                productUseCase.deleteProduct(nonExistentId);
            });
        }
    }

    @Nested
    @DisplayName("Obtener Todos los Productos")
    class GetAllProducts {

        @Test
        @DisplayName("Debería obtener todos los productos")
        void shouldGetAllProducts() {
            List<Product> productList = new ArrayList<>();
            productList.add(testProduct);
            productList.add(new Product(
                UUID.randomUUID(),
                "SKU-002",
                "Segundo Producto",
                "Descripción dos",
                new BigDecimal("199.99"),
                30,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "HOME"
            ));

            when(productRepository.findAll()).thenReturn(productList);

            List<Product> result = productUseCase.getAllProducts();

            assertNotNull(result);
            assertEquals(2, result.size());
            verify(productRepository).findAll();
        }

        @Test
        @DisplayName("Debería retornar lista vacía cuando no hay productos")
        void shouldReturnEmptyListWhenNoProducts() {
            when(productRepository.findAll()).thenReturn(new ArrayList<>());

            List<Product> result = productUseCase.getAllProducts();

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Reducir Stock")
    class ReduceStock {

        @Test
        @DisplayName("Debería reducir el stock exitosamente")
        void shouldReduceStockSuccessfully() {
            when(productRepository.findById(testProductId)).thenReturn(Optional.of(testProduct));
            when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
                Product p = invocation.getArgument(0);
                return p;
            });

            Product result = productUseCase.reduceStock(testProductId, 10);

            assertNotNull(result);
            assertEquals(40, result.getStockQuantity());
            verify(productRepository).findById(testProductId);
            verify(productRepository).save(any(Product.class));
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el producto no existe")
        void shouldThrowExceptionWhenProductNotFound() {
            UUID nonExistentId = UUID.randomUUID();
            when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            assertThrows(IllegalArgumentException.class, () -> {
                productUseCase.reduceStock(nonExistentId, 10);
            });
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el stock es insuficiente")
        void shouldThrowExceptionWhenInsufficientStock() {
            when(productRepository.findById(testProductId)).thenReturn(Optional.of(testProduct));

            assertThrows(IllegalStateException.class, () -> {
                productUseCase.reduceStock(testProductId, 100);
            });
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando la cantidad a reducir es negativa")
        void shouldThrowExceptionWhenQuantityIsNegative() {
            when(productRepository.findById(testProductId)).thenReturn(Optional.of(testProduct));

            assertThrows(IllegalArgumentException.class, () -> {
                productUseCase.reduceStock(testProductId, -5);
            });
        }
    }
}