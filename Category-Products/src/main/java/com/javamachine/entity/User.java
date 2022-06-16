package com.javamachine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;
    @Column(unique = true)
    private String email;
    private boolean isActive;
//    private String roles;
   
  
    @ManyToMany(fetch = FetchType.EAGER,cascade = {
    		
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "user_groups",
            joinColumns =@JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"
    ))
    private Set<Roles> roles= new HashSet<>();

    public User() {
		
	}
	
    
    public User(int id, String userName, String password, String email, boolean isActive, Set<Roles> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.isActive = isActive;
		this.roles = roles;
	}


//	public User(int id, String userName, String password, String email,boolean isActive,String roles) {
//		this.id = id;
//		this.userName = userName;
//		this.password = password;
//		this.email = email;
//		this.isActive = isActive;
////		this.roles = roles;
//	}
//	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Set<Roles> getRoles() {
		return roles;
	}


	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

//
//	public String getRoles() {
//		return roles;
//	}
//
//
//	public void setRoles(String roles) {
//		this.roles = roles;
//	}
    
	
    
}