package com.inventoryservice.infrastructure.adapter;


import com.inventoryservice.infrastructure.repository.ProductJpaAdapter;
import com.inventoryservice.domain.model.Product;
import com.inventoryservice.domain.port.ProductRepository;
import com.inventoryservice.infrastructure.repository.ProductEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Transactional
public class ProductJpaRepository implements ProductRepository {

    private final ProductJpaAdapter productJpaAdapter;

    public ProductJpaRepository(ProductJpaAdapter productJpaAdapter) {
        this.productJpaAdapter = productJpaAdapter;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = toEntity(product);
        ProductEntity savedEntity = productJpaAdapter.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(UUID id) {
        return productJpaAdapter.findById(id).map(this::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findBySku(String sku) {
        return productJpaAdapter.findBySku(sku).map(this::toDomain);
    }

    @Override
    public boolean deleteById(UUID id) {
        if (productJpaAdapter.existsById(id)) {
            productJpaAdapter.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productJpaAdapter.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return productJpaAdapter.existsBySku(sku);
    }

    private ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        if (product.getId() != null) {
            entity.setId(product.getId());
        }
        entity.setSku(product.getSku());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setStockQuantity(product.getStockQuantity());
        entity.setCategory(product.getCategory());
        entity.setCreatedAt(product.getCreatedAt() != null ? product.getCreatedAt() : LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    private Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getSku(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStockQuantity(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getCategory()
        );
    }
}