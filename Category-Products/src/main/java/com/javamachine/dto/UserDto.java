package com.javamachine.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

public class UserDto {

	@NotEmpty(message= "userName should not be empty")
	private String userName;
    
	@NotEmpty(message = "password should not be empty")
	private String password;
    
    @NotEmpty(message = "email should not be empty")
    @Column(unique = true)
    private String email;

    public UserDto() {
	}
    
    
	public UserDto( String userName,String password,String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;
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
	
	
    
    
}
