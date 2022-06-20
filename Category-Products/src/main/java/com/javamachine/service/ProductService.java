package com.javamachine.service;

import com.javamachine.entity.Product;
import com.javamachine.exception.ProductNotFoundException;
import com.javamachine.dto.CartDto;
import com.javamachine.entity.Cart;
import com.javamachine.entity.Category;
import com.javamachine.repository.CartRepository;
import com.javamachine.repository.ProductRepository;
import com.javamachine.repository.UserRepository;
import com.javamachine.util.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public Product getProduct(int id){
        Optional<Product> product = productRepository.findById(id);

        return product.get();
    }
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public Product updateProduct(int id,Product product){
    	product.setProductId(id);
        return productRepository.save(product);
    }
    public void deleteProduct(int id){
    	productRepository.deleteById(id);
    }
    
    
}