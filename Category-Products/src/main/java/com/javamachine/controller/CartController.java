package com.javamachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javamachine.exception.CartEmptyException;
import com.javamachine.exception.ProductNotFoundException;
import com.javamachine.service.CartServices;


@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartServices cartServices;
	
	@GetMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getCartById(@RequestParam("id")int cartId) throws CartEmptyException{
		return new ResponseEntity<> (cartServices.getCartById(cartId),HttpStatus.OK);
	}
	
	
	@GetMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getCartByName(@RequestParam("cartName")String cartName) throws CartEmptyException{
		return new ResponseEntity<> (cartServices.getCartByName(cartName),HttpStatus.OK);
	}
	
	@GetMapping()
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<?> getCartOfLoggedInUser() throws CartEmptyException{
		return new ResponseEntity<> (cartServices.getCartOfLoggedUser(),HttpStatus.OK);
	}
	
	@PostMapping("/shop/{id}")
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<?> addProductsToCart(@PathVariable(value = "id")int productId) throws ProductNotFoundException{
    	System.out.println("************************************herer");
    	return new ResponseEntity<> (cartServices.assignProductToUser(productId),HttpStatus.OK);
    }
}
