package com.javamachine.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javamachine.dto.AuthRequest;
import com.javamachine.exception.LoginDetailInvalidException;
import com.javamachine.repository.UserRepository;
import com.javamachine.util.JwtUtil;

@RestController
public class LoginController {

	
    @Autowired
    private JwtUtil jwtUtil;
	 
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository userRepository;
    
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
	
//	@RequestMapping(value = "/hello")
//	public String loginMEthod() {
//		return "YOU HAVE SUCCESFULLY LOGGED IN !";
//	}
	
	
//	 @PostMapping("/authenticate")
//	    public String generateToken(@RequestBody @Valid AuthRequest authRequest) throws LoginDetailInvalidException , Exception{
//	        try {
//	        	System.out.println(authRequest.getPassword()+authRequest.getEmail()+"***************************++++");
//	        
////	        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////	        	if(passwordEncoder.matches(authRequest.getPassword(),userRepository.findByEmail(authRequest.getEmail()).get().getPassword())) {	
//
////	        		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),userRepository.findByEmail(authRequest.getEmail()).get().getPassword()));
//	        	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
////	        	}
////	        	else {
////	        		throw new LoginDetailInvalidException("inavalid username OR password ??");
////	        	}
//	            
//	        } catch (Exception ex) {
//	            throw new LoginDetailInvalidException(ex.getMessage());
//	        }
//	        return jwtUtil.generateToken(authRequest.getEmail());
//	    }
	 
	 @PostMapping("/authenticate")
	    public String generateToken(@RequestBody @Valid AuthRequest authRequest) throws LoginDetailInvalidException{
	        try {
	        	System.out.println(authRequest.getPassword()+authRequest.getEmail()+"***************************++++");
	        

	        	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
	   
	        } catch (BadCredentialsException ex) {
	            throw new LoginDetailInvalidException("username or password invalid");
	        }
	        return jwtUtil.generateToken(authRequest.getEmail());
	    }
}
