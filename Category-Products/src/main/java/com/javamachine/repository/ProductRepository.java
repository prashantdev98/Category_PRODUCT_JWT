package com.javamachine.repository;

import com.javamachine.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

	List<Product> findProductByProductName(String productName);
	List<Product> findProductByProductNameLike(String productName);
	List<Product> findProductByProductNameStartsWith(String productName);
	List<Product> findProductByProductNameEndsWith(String productName);
	boolean existsProductByProductName(String productName);
	boolean existsProductByProductNameLike(String productName);
	boolean existsProductByProductNameStartsWith(String productName);
	boolean existsProductByProductNameEndsWith(String productName);
}