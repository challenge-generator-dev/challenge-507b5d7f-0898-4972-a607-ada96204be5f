package com.inventoryservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductJpaAdapter extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findBySku(String sku);
    boolean existsBySku(String sku);
}