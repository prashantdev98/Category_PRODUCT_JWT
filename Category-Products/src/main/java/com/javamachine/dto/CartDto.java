package com.javamachine.dto;

import java.util.List;

import com.javamachine.entity.Product;

public class CartDto {

	
	private int cartId;
	private String cartName;
	
	private List<Product> productList;

	public CartDto() {
	}
	
	public CartDto(int cartId, String cartName, List<Product> productList) {
		super();
		this.cartId = cartId;
		this.cartName = cartName;
		this.productList = productList;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getCartName() {
		return cartName;
	}

	public void setCartName(String cartName) {
		this.cartName = cartName;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	
}
