package com.benz.redis.services;

import com.benz.redis.dao.ProductDAO;
import com.benz.redis.exception.DataNotFoundException;
import com.benz.redis.model.Product;
import com.benz.redis.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private ProductDAO productDAO;
    private RedisTemplate<String,Product> redisTemplate;
    private ChannelTopic channelTopic;
    final private static String HASH_KEY="Product";

    @Autowired
    public ProductService(ProductDAO productDAO,RedisTemplate<String,Product> redisTemplate,ChannelTopic channelTopic)
    {
        this.productDAO=productDAO;
        this.redisTemplate=redisTemplate;
        this.channelTopic=channelTopic;
    }

    @CacheEvict(value = HASH_KEY,allEntries = true,cacheNames = "Product")
    public Product saveProduct(Product product)
    {
        return productDAO.saveProduct(product);
    }

    @Cacheable(key = "#productId",value = "Product",unless = "#result.price>8000",cacheNames = "Product")
    public Product getProduct(int productId)
    {
         return productDAO.findProduct(productId)
                 .orElseThrow(()->new DataNotFoundException(String.format("product is not found with %d",productId)));
    }

    @Cacheable(value = "Product",cacheNames = "Product")
    public List<Product> getProducts()
    {
        Map<Integer,Product> products=productDAO.findAllProducts()
                .orElseThrow(()->new DataNotFoundException(String.format("No Product in the database")));

        return new ArrayList<>(products.values());
    }


    @CacheEvict(value = HASH_KEY,allEntries = true,cacheNames = "Product")
    public Product updateProduct(Product product)
    {
        return productDAO.updateProduct(product)
                .orElseThrow(()->new DataNotFoundException(String.format("product is not found with %d",product.getProductId())));
    }


    @CacheEvict(key = "#productId",value = "Product",allEntries = true,cacheNames = "Product")
    public void deleteProduct(int productId)
    {
        productDAO.deleteProduct(productId);
    }


    public void sendProduct(int productId)
    {
        Product product=productDAO.findProduct(productId)
                .orElseThrow(()->new DataNotFoundException(String.format("product is not found with %d",productId)));

        redisTemplate.convertAndSend(channelTopic.getTopic(),product);
    }

}
