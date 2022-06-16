package com.javamachine.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javamachine.exception.InvalidUriException;
import com.javamachine.securtiy.MyUserDetailService;
import com.javamachine.util.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	MyUserDetailService userDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  throws ServletException, IOException {
		System.out.println("*******************ONCE FILTER****************************************************");
		JSONObject json = new JSONObject();
	  	ObjectMapper mapper = new ObjectMapper();
//		if(String.valueOf(request.getRequestURI()).matches("/user/admin/update/^null|$/*")){
//			System.out.println("****************REGEX**********************");
//		}
		 if(request.getRequestURI().equals("/user/admin/update/") || request.getRequestURI().equals("/user/admin/update")) {
		 //create Json Object
		    System.out.println("*****************************INISDE/***************************");
//			JSONObject json = new JSONObject();
//		  	ObjectMapper mapper = new ObjectMapper();
		    json.accumulate("errorMessage","Email,Code PARAM MISSING");
		    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		    response.setStatus(404);
//		    response.getWriter().write(json.toString());
		    mapper.writeValue(response.getWriter(),json.toMap());
		    filterChain.doFilter(request, response);	
		 }else if(request.getRequestURI().equals("/user/admin/endWith/") || request.getRequestURI().equals("/user/admin/endWith") || request.getRequestURI().equals("/user/admin/between/") || request.getRequestURI().equals("/user/admin/between") || request.getRequestURI().equals("/user/admin/startWith")) {
			 
			 json.accumulate("errorMessage","userName PARAM MISSING");
			    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			    response.setStatus(404);
			    mapper.writeValue(response.getWriter(),json.toMap());
			 
		 }else{

			String authorizationHeader = request.getHeader("Authorization");
			String token = null;
			String userName = null;
			if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				token = authorizationHeader.substring(7);
				userName = jwtUtil.extractUsername(token);
				if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
					UserDetails userDetails = userDetailService.loadUserByUsername(userName);
					if(jwtUtil.validateToken(token, userDetails)) {
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName,null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
						
					}
				}
				
			}
			filterChain.doFilter(request, response);	
			
			
		}
	}

}
