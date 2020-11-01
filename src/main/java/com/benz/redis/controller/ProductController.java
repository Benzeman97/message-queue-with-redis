package com.benz.redis.controller;

import com.benz.redis.model.Product;
import com.benz.redis.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService=productService;
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId)
    {
        if(productId!=0)
        return new ResponseEntity<>(productService.getProduct(productId),HttpStatus.OK);
        else
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getProducts()
    {
        return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
    }

    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> saveProduct(@RequestBody Product product)
    {
         if(product!=null)
             return ResponseEntity.ok(productService.saveProduct(product));
         else
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping(value = "/update",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> updateProduct(@RequestBody Product product)
    {
        if(product.getProductId()!=0)
            return ResponseEntity.ok(productService.updateProduct(product));
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int productId)
    {
        if(productId!=0)
            productService.deleteProduct(productId);
        else
            throw new IllegalArgumentException("product id should not be 0");
    }

    @GetMapping("/send/{pid}")
    public void sendProduct(@PathVariable("pid") int productId)
    {
           productService.sendProduct(productId);
    }
}
