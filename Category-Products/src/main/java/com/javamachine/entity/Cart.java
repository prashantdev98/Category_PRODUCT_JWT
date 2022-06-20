package com.javamachine.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	
	private String cartName;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
//    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
//    @JoinColumn(name = "cart_id",referencedColumnName = "cartId")
//	private List<Product> products;
//    
    
    @ManyToMany(fetch = FetchType.EAGER,cascade = {
    		
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "user_orders",
            joinColumns =@JoinColumn(name = "cart_cartId"),
            inverseJoinColumns = @JoinColumn(name = "product_productId"
    ))
    private List<Product> products;

    
    
    public Cart() {
    	
	}
    
    
	public Cart(int cartId, String cartName, User user, List<Product> products) {
	super();
	this.cartId = cartId;
	this.cartName = cartName;
	this.user = user;
	this.products = products;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
    
    
    
    
}
