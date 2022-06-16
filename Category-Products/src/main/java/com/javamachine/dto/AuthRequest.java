package com.javamachine.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

	@NotEmpty(message = "Email should not be Empty")
	@Email(message = "give proper Email Format")
    private String email;
	
	@NotEmpty(message = "password should not be Empty")
    private String password;
    
    public AuthRequest() {
	}
    
    
	public AuthRequest(String userName, String password) {
		super();
		this.email = userName;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}