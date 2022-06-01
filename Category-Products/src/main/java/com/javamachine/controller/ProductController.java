package com.javamachine.controller;


import com.javamachine.entity.Product;
import com.javamachine.entity.Category;
import com.javamachine.repository.ProductRepository;
import com.javamachine.service.ProductService;
import com.javamachine.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
	
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductRepository productRepository; 

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }
    
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id){
        return productService.getProduct(id);
    }

	
    
    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    @PutMapping("/{id}")
    public Product editProduct(@PathVariable int id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){
    	productService.deleteProduct(id);
    }
}