package com.javamachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javamachine.dto.AuthRequest;
import com.javamachine.util.JwtUtil;

@RestController
public class LoginController {

	
    @Autowired
    private JwtUtil jwtUtil;
	 
    @Autowired
    private AuthenticationManager authenticationManager;
	
//	@RequestMapping(value = "/hello")
//	public String loginMEthod() {
//		return "YOU HAVE SUCCESFULLY LOGGED IN !";
//	}
	
	
	 @PostMapping("/authenticate")
	    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
	            );
	        } catch (Exception ex) {
	            throw new Exception("inavalid username/password");
	        }
	        return jwtUtil.generateToken(authRequest.getUserName());
	    }
}
