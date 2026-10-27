package com.inventoryservice.application.usecase;

import com.inventoryservice.domain.model.Product;
import com.inventoryservice.domain.port.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProductUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ProductUseCase.class);
    private final ProductRepository productRepository;

    public ProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String sku, String name, String description, 
                                  BigDecimal price, Integer stockQuantity, String category) {
        logger.info("Creating new product with SKU: {}", sku);
        
        if (productRepository.existsBySku(sku)) {
            logger.warn("Product with SKU {} already exists", sku);
            throw new IllegalArgumentException("Product with SKU " + sku + " already exists");
        }
        
        Product product = new Product(
            UUID.randomUUID(),
            sku,
            name,
            description,
            price,
            stockQuantity,
            LocalDateTime.now(),
            LocalDateTime.now(),
            category
        );
        
        Product savedProduct = productRepository.save(product);
        logger.info("Product created successfully with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductById(UUID id) {
        logger.debug("Fetching product by ID: {}", id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            logger.warn("Product not found with ID: {}", id);
        }
        return product;
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductBySku(String sku) {
        logger.debug("Fetching product by SKU: {}", sku);
        return productRepository.findBySku(sku);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        logger.debug("Fetching all products");
        List<Product> products = productRepository.findAll();
        logger.info("Found {} products", products.size());
        return products;
    }

    public Product updateProduct(UUID id, String name, String description, 
                                  BigDecimal price, Integer stockQuantity, String category) {
        logger.info("Updating product with ID: {}", id);
        
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Product not found for update with ID: {}", id);
                return new IllegalArgumentException("Product not found with ID: " + id);
            });
        
        Product updatedProduct = existingProduct.withUpdatedInfo(
            name, description, price, stockQuantity, category
        );
        
        Product savedProduct = productRepository.save(updatedProduct);
        logger.info("Product updated successfully: {}", savedProduct.getId());
        return savedProduct;
    }

    public boolean deleteProduct(UUID id) {
        logger.info("Deleting product with ID: {}", id);
        
        boolean exists = productRepository.findById(id).isPresent();
        if (!exists) {
            logger.warn("Product not found for deletion with ID: {}", id);
            return false;
        }
        
        boolean deleted = productRepository.deleteById(id);
        if (deleted) {
            logger.info("Product deleted successfully: {}", id);
        } else {
            logger.error("Failed to delete product: {}", id);
        }
        return deleted;
    }

    public Product reduceStock(UUID id, int quantity) {
        logger.info("Reducing stock for product ID: {} by quantity: {}", id, quantity);
        
        Product product = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Product not found for stock reduction: {}", id);
                return new IllegalArgumentException("Product not found with ID: " + id);
            });
        
        int currentStock = product.getStockQuantity();
        if (currentStock < quantity) {
            logger.error("Insufficient stock for product ID: {}. Current: {}, Requested: {}", 
                id, currentStock, quantity);
            throw new IllegalStateException("Insufficient stock. Available: " + currentStock);
        }
        
        int newStock = currentStock - quantity;
        Product updatedProduct = product.withUpdatedInfo(
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            newStock,
            product.getCategory()
        );
        
        Product savedProduct = productRepository.save(updatedProduct);
        logger.info("Stock reduced successfully. New stock for product {}: {}", id, newStock);
        return savedProduct;
    }
}