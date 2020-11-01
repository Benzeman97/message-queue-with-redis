package com.benz.redis.dao.impl;

import com.benz.redis.dao.ProductDAO;
import com.benz.redis.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class ProductDAOImpl implements ProductDAO {

    final private static String HASH_KEY="Product";

    private HashOperations<String,Integer,Product> hashOperations;

    @Autowired
    public ProductDAOImpl(RedisTemplate<String,Product> restTemplate)
    {
        this.hashOperations=restTemplate.opsForHash();
    }


    @Override
    public Optional<Product> findProduct(int productId) {
        System.out.println("call all the products from DB");
        return Optional.of(hashOperations.get(HASH_KEY,productId));
    }

    @Override
    public Optional<Map<Integer,Product>> findAllProducts() {
        System.out.println("call all the products from DB");
        return Optional.of(hashOperations.entries(HASH_KEY));
    }

    @Override
    public Product saveProduct(Product product) {
        try {
            hashOperations.put(HASH_KEY, product.getProductId(), product);
            return product;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        try {
            hashOperations.put(HASH_KEY, product.getProductId(), product);
            return Optional.of(product);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteProduct(int productId) {
         hashOperations.delete(HASH_KEY,productId);
    }
}
