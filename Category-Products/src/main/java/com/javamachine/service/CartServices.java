package com.javamachine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamachine.dto.CartDto;
import com.javamachine.entity.Cart;
import com.javamachine.entity.Product;
import com.javamachine.exception.CartEmptyException;
import com.javamachine.exception.ProductNotFoundException;
import com.javamachine.repository.CartRepository;
import com.javamachine.repository.ProductRepository;
import com.javamachine.repository.UserRepository;
import com.javamachine.util.SecurityUtils;

@Service
public class CartServices {

	@Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
	
	public CartDto assignProductToUser(int productId) throws ProductNotFoundException{
    	if(!productRepository.existsById(productId)) {
    		throw new ProductNotFoundException("Product does not exits");
    	}
    	CartDto responseCart = new CartDto();
    	System.out.println(SecurityUtils.getCurrentUsername());
    	Cart cart = userRepository.findByEmail(SecurityUtils.getCurrentUsername().get()).get().getCart();
    	System.out.println(cart);
    	Product product = productRepository.getById(productId);
//    	product.getCart().add(cart);
//    	productRepository.save(product);
    	
    	if(!cart.getProducts().isEmpty()) {
    		List<Product> productList1 = cart.getProducts();
    		productList1.add(product);
    		cart.setProducts(productList1);
    		responseCart.setCartId(cart.getCartId());
        	responseCart.setCartName(cart.getCartName());
        	responseCart.setProductList(cart.getProducts());
        	cartRepository.save(cart);
        	return responseCart;
    	}
    	List<Product> productList = new ArrayList<Product>();
    	productList.add(product);
    	cart.setProducts(productList);
    	responseCart.setCartId(cart.getCartId());
    	responseCart.setCartName(cart.getCartName());
    	responseCart.setProductList(cart.getProducts());
    	cartRepository.save(cart);
    	return responseCart;
    }
	
	public CartDto getCartOfLoggedUser() throws CartEmptyException{
		
		CartDto responseCart = new CartDto();
		Cart cart = userRepository.findByEmail(SecurityUtils.getCurrentUsername().get()).get().getCart();
		if(cart.getProducts().isEmpty()) {
			throw new CartEmptyException("Your Cart is Empty");
		}
		responseCart.setCartId(cart.getCartId());
		responseCart.setCartName(cart.getCartName());
		responseCart.setProductList(cart.getProducts());
		return responseCart;
	}
	
	public CartDto getCartById(int cartId) throws CartEmptyException{
		if(!cartRepository.existsCartByCartId(cartId)) {
			throw new CartEmptyException("Cart is Not present");
		}
		Cart cart = cartRepository.getById(cartId);
		if(cart.getProducts().isEmpty()) {
			throw new CartEmptyException("This Cart is Empty");
		}
		CartDto responseCart = new CartDto();
		responseCart.setCartId(cart.getCartId());
		responseCart.setCartName(cart.getCartName());
		responseCart.setProductList(cart.getProducts());
		return responseCart;
	}
	
	public CartDto getCartByName(String cartName) throws CartEmptyException{
		if(!cartRepository.existsCartByCartName(cartName)) {
			throw new CartEmptyException("Cart is Not present");
		}
		Cart cart = cartRepository.findByCartName(cartName);
		if(cart.getProducts().isEmpty()) {
			throw new CartEmptyException("This Cart is Empty");
		}
		CartDto responseCart = new CartDto();
		responseCart.setCartId(cart.getCartId());
		responseCart.setCartName(cart.getCartName());
		responseCart.setProductList(cart.getProducts());
		return responseCart;
	}
}
