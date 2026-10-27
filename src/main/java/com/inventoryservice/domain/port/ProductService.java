package com.inventoryservice.domain.port;

import com.inventoryservice.domain.model.Product;
import java.util.List;
import java.util.UUID;

/**
 * Puerto de entrada para operaciones de negocio relacionadas con productos.
 * Define los contratos que la capa de aplicación debe implementar para exponer la funcionalidad del dominio.
 */
public interface ProductService {
    /**
     * Crea un nuevo producto.
     * @param product el producto a crear
     * @return el producto creado
     * @throws IllegalArgumentException si el producto es inválido
     * @throws IllegalStateException si ya existe un producto con el mismo SKU
     */
    Product createProduct(Product product);

    /**
     * Obtiene un producto por su ID.
     * @param id el ID del producto
     * @return el producto encontrado
     * @throws java.util.NoSuchElementException si el producto no existe
     */
    Product getProductById(UUID id);

    /**
     * Actualiza un producto existente.
     * @param id el ID del producto a actualizar
     * @param product los nuevos datos del producto
     * @return el producto actualizado
     * @throws java.util.NoSuchElementException si el producto no existe
     * @throws IllegalArgumentException si los datos del producto son inválidos
     */
    Product updateProduct(UUID id, Product product);

    /**
     * Elimina un producto por su ID.
     * @param id el ID del producto a eliminar
     * @throws java.util.NoSuchElementException si el producto no existe
     */
    void deleteProduct(UUID id);

    /**
     * Obtiene todos los productos.
     * @return una lista de todos los productos
     */
    List<Product> getAllProducts();

    /**
     * Reduce el stock de un producto.
     * @param id el ID del producto
     * @param quantity la cantidad a reducir
     * @return el producto con el stock actualizado
     * @throws java.util.NoSuchElementException si el producto no existe
     * @throws IllegalArgumentException si la cantidad es inválida o el stock es insuficiente
     */
    Product reduceStock(UUID id, int quantity);
}