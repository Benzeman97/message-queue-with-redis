package com.benz.redis.dao;

import com.benz.redis.model.Product;

import java.util.Map;
import java.util.Optional;

public interface ProductDAO {

    Optional<Product> findProduct(int productId);
    Optional<Map<Integer,Product>> findAllProducts();
    Product saveProduct(Product product);
    Optional<Product> updateProduct(Product product);
    void deleteProduct(int productId);
}
