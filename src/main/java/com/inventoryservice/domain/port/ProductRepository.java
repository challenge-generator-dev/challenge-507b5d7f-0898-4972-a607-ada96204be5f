package com.inventoryservice.domain.port;

import com.inventoryservice.domain.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para operaciones de persistencia de productos.
 * Define los contratos que la capa de infraestructura debe implementar para interactuar con la base de datos.
 */
public interface ProductRepository {
    /**
     * Guarda un producto en el repositorio.
     * @param product el producto a guardar
     * @return el producto guardado
     */
    Product save(Product product);

    /**
     * Busca un producto por su ID.
     * @param id el ID del producto
     * @return un Optional que contiene el producto si existe
     */
    Optional<Product> findById(UUID id);

    /**
     * Busca un producto por su SKU.
     * @param sku el SKU del producto
     * @return un Optional que contiene el producto si existe
     */
    Optional<Product> findBySku(String sku);

    /**
     * Elimina un producto por su ID.
     * @param id el ID del producto a eliminar
     * @return true si el producto fue eliminado, false si no existía
     */
    boolean deleteById(UUID id);

    /**
     * Obtiene todos los productos del repositorio.
     * @return una lista de todos los productos
     */
    List<Product> findAll();

    /**
     * Verifica si existe un producto con el SKU dado.
     * @param sku el SKU a verificar
     * @return true si existe un producto con ese SKU, false en caso contrario
     */
    boolean existsBySku(String sku);
}