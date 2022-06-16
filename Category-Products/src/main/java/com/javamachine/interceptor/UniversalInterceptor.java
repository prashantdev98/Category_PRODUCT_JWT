package com.javamachine.interceptor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.Http11AprProtocol;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.NestedServletException;

import com.javamachine.exception.InvalidUriException;
import com.javamachine.exception.JsonFormatNotProperException;
import com.javamachine.exception.UserAlreadyExistException;

@Component
public class UniversalInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		System.out.println("*************************INTERCEPTOR*************************");
//		********************UPDATE USER**********************
//		if(request.getRequestURI().equals("/user/admin/update/")){
//			throw new InvalidUriException("Email,Code,PARAM MISSING");
//		}
//		else if(request.getRequestURI().equals("/admin/update/{email}")) {
//			throw new InvalidUriException("Code,PARAM MISSING");
//		}else if(request.getRequestURI().equals("/admin/update/{}")) {
//			throw new InvalidUriException("Code,PARAM MISSING");
//		}
//		
//		 final Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//		System.out.println(pathVariables);
//		for (Map.Entry<String,String> entry: pathVariables.entrySet()) {
//			System.out.println(entry.getKey()+"******"+entry.getValue());
//		}
//		
////		********************GET USER**********************
//		if(request.getRequestURI().equals("/user/admin/endWith/")) {
//			throw new InvalidUriException("Name PARAM MISSING");
//		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		System.out.println(request.getRequestURI());
		
//		try {
//	
//				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//		    	StringBuilder stringJson = new StringBuilder();
//			    String responseLine = null;
//			    while ((responseLine = br.readLine()) != null) {
//			    	stringJson.append(responseLine.trim());
//			    }
////
//				JSONObject jsonObj = new JSONObject(stringJson.toString());
////				JSONObject jsonObj = new JSONObject(br.toString());
//				System.out.println(jsonObj+"***********************FROM INTERCEPTOR");
////				System.out.println(jsonObj.get("email")+ "********************EMAIL");
////					System.out.println("*********************/********//******************************");
//				return super.preHandle(request, response, handler);
//
//				
//		}catch (JSONException e) {
//			throw new JsonFormatNotProperException("Your Json is not Proper VVV");
//		}
//		
		
		return super.preHandle(request, response, handler);
		
		
		
		
		
		
		
		
		
		
		
		
		
//		try {
//			return super.preHandle(request, response, handler);
//		}catch(HttpMessageNotReadableException ex) {
//			throw new JsonFormatNotProperException("Your Json is not Proper VVV");
//		}
	}
	

	
}
