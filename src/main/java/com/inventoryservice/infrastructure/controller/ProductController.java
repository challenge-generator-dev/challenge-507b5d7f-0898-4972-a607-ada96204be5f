package com.inventoryservice.infrastructure.controller;

import com.inventoryservice.domain.model.Product;
import com.inventoryservice.infrastructure.controller.dto.ProductRequest;
import com.inventoryservice.infrastructure.controller.dto.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductUseCaseWrapper productUseCase;

    public ProductController(ProductUseCaseWrapper productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        logger.info("POST /api/v1/products - Creating product with SKU: {}", request.sku());
        try {
            Product product = productUseCase.createProduct(
                request.sku(),
                request.name(),
                request.description(),
                request.price(),
                request.stockQuantity(),
                request.category()
            );
            ProductResponse response = ProductResponse.fromDomain(product);
            logger.info("Product created successfully with ID: {}", product.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to create product: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id) {
        logger.info("GET /api/v1/products/{}", id);
        return productUseCase.getProductById(id)
            .map(product -> {
                logger.debug("Product found: {}", product.getId());
                return ResponseEntity.ok(ProductResponse.fromDomain(product));
            })
            .orElseGet(() -> {
                logger.warn("Product not found: {}", id);
                return ResponseEntity.notFound().build();
            });
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductResponse> getProductBySku(@PathVariable String sku) {
        logger.info("GET /api/v1/products/sku/{}", sku);
        return productUseCase.getProductBySku(sku)
            .map(product -> {
                logger.debug("Product found with SKU: {}", sku);
                return ResponseEntity.ok(ProductResponse.fromDomain(product));
            })
            .orElseGet(() -> {
                logger.warn("Product not found with SKU: {}", sku);
                return ResponseEntity.notFound().build();
            });
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        logger.info("GET /api/v1/products - Fetching all products");
        
        List<Product> products;
        if (category != null || minPrice != null || maxPrice != null) {
            logger.debug("Filtering products - category: {}, minPrice: {}, maxPrice: {}", 
                category, minPrice, maxPrice);
            products = productUseCase.filterProducts(category, minPrice, maxPrice);
        } else {
            products = productUseCase.getAllProducts();
        }
        
        List<ProductResponse> responses = products.stream()
            .map(ProductResponse::fromDomain)
            .collect(Collectors.toList());
        
        logger.info("Returning {} products", responses.size());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable UUID id, 
            @Valid @RequestBody ProductRequest request) {
        logger.info("PUT /api/v1/products/{}", id);
        try {
            Product product = productUseCase.updateProduct(
                id,
                request.name(),
                request.description(),
                request.price(),
                request.stockQuantity(),
                request.category()
            );
            ProductResponse response = ProductResponse.fromDomain(product);
            logger.info("Product updated successfully: {}", id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to update product {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        logger.info("DELETE /api/v1/products/{}", id);
        boolean deleted = productUseCase.deleteProduct(id);
        if (deleted) {
            logger.info("Product deleted successfully: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Product not found for deletion: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/reduce-stock")
    public ResponseEntity<ProductResponse> reduceStock(
            @PathVariable UUID id, 
            @RequestParam int quantity) {
        logger.info("PUT /api/v1/products/{}/reduce-stock - quantity: {}", id, quantity);
        try {
            Product product = productUseCase.reduceStock(id, quantity);
            ProductResponse response = ProductResponse.fromDomain(product);
            logger.info("Stock reduced successfully for product: {}", id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.warn("Product not found: {}", id);
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            logger.warn("Insufficient stock for product {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}