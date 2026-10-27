package com.inventoryservice.infrastructure.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "El SKU es obligatorio")
        @Size(min = 3, max = 50, message = "El SKU debe tener entre 3 y 50 caracteres")
        String sku,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 1, max = 200, message = "El nombre debe tener entre 1 y 200 caracteres")
        String name,

        @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
        String description,

        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
        BigDecimal price,

        @NotNull(message = "La cantidad en stock es obligatoria")
        Integer stockQuantity,

        @NotBlank(message = "La categoría es obligatoria")
        @Size(max = 100, message = "La categoría no puede exceder 100 caracteres")
        String category
) {
}