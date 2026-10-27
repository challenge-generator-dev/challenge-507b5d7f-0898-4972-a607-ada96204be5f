package com.inventoryservice.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad de dominio que representa un producto en el inventario.
 * Contiene los atributos esenciales para la gestión del inventario y validaciones básicas.
 */
public class Product {
    private final UUID id;
    private final String sku;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Integer stockQuantity;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String category;

    public Product(UUID id, String sku, String name, String description, BigDecimal price,
                  Integer stockQuantity, LocalDateTime createdAt, LocalDateTime updatedAt, String category) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.sku = validateSku(sku);
        this.name = validateName(name);
        this.description = description;
        this.price = validatePrice(price);
        this.stockQuantity = validateStockQuantity(stockQuantity);
        this.createdAt = Objects.requireNonNull(createdAt, "La fecha de creación no puede ser nula");
        this.updatedAt = Objects.requireNonNull(updatedAt, "La fecha de actualización no puede ser nula");
        this.category = validateCategory(category);
    }

    private String validateSku(String sku) {
        if (sku == null || sku.trim().isEmpty()) {
            throw new IllegalArgumentException("El SKU no puede ser nulo o vacío");
        }
        if (sku.length() > 50) {
            throw new IllegalArgumentException("El SKU no puede exceder 50 caracteres");
        }
        return sku.trim();
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }
        return name.trim();
    }

    private BigDecimal validatePrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("El precio no puede ser nulo");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
        return price;
    }

    private Integer validateStockQuantity(Integer stockQuantity) {
        if (stockQuantity == null) {
            throw new IllegalArgumentException("La cantidad en stock no puede ser nula");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("La cantidad en stock no puede ser negativa");
        }
        return stockQuantity;
    }

    private String validateCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede ser nula o vacía");
        }
        if (category.length() > 50) {
            throw new IllegalArgumentException("La categoría no puede exceder 50 caracteres");
        }
        return category.trim();
    }

    public UUID getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getCategory() {
        return category;
    }

    public Product withUpdatedInfo(String name, String description, BigDecimal price, Integer stockQuantity, String category) {
        return new Product(
            this.id,
            this.sku,
            name != null ? name : this.name,
            description != null ? description : this.description,
            price != null ? price : this.price,
            stockQuantity != null ? stockQuantity : this.stockQuantity,
            this.createdAt,
            LocalDateTime.now(),
            category != null ? category : this.category
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) && sku.equals(product.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sku='" + sku + ''' +
                ", name='" + name + ''' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category='" + category + ''' +
                '}';
    }
}