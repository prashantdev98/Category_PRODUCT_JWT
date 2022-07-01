package com.javamachine.controller;


import com.javamachine.entity.Product;
import com.javamachine.exception.ProductNotFoundException;
import com.javamachine.entity.Category;
import com.javamachine.repository.ProductRepository;
import com.javamachine.service.ProductService;
import com.javamachine.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CUSTOMER')")
    public List<Product> getProducts(){
        return productService.getProducts();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Product getProduct(@PathVariable int id){
        return productService.getProduct(id);
    }

    @GetMapping("/full/productName")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> getProductByName(@RequestParam("productName") String productName) throws ProductNotFoundException{
        return new ResponseEntity<>(productService.getProductByName(productName),HttpStatus.OK);
    }
    
    @GetMapping("/between")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> getProductByNameBetween(@RequestParam("productName") String productName) throws ProductNotFoundException{
        return new ResponseEntity<>(productService.getProductByNameBetween(productName),HttpStatus.OK);
    }
    
    
    @GetMapping("/startsWith")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> getProductByNameStartsWith(@RequestParam("productName") String productName) throws ProductNotFoundException{
        return new ResponseEntity<>(productService.getProductByNameStartsWith(productName),HttpStatus.OK);
    }
    

    @GetMapping("/endsWith")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> getProductByNameEndsWith(@RequestParam("productName") String productName) throws ProductNotFoundException{
        return new ResponseEntity<>(productService.getProductByNameEndsWith(productName),HttpStatus.OK);
    }
    
    
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Product editProduct(@PathVariable int id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }
    
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteProduct(@PathVariable int id){
    	productService.deleteProduct(id);
    }
    
    
}