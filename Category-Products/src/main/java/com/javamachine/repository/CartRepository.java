package com.javamachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javamachine.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	boolean existsCartByCartId(int cartId);
	boolean existsCartByCartName(String cartName);
	Cart findByCartName(String cartName);
}
