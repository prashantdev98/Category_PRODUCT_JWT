//package com.javamachine.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Component;
//
//import com.javamachine.exception.InvalidUriException;
//
//@Component
//public class UniversalFilter implements  Filter{
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
//		 
//		System.out.println("********************FILTER****************************************");
////		if (request instanceof HttpServletRequest) {
////			 String url = ((HttpServletRequest)request).getRequestURI().toString();
////			 System.out.println("*****************************"+url+"******************");
////			 if(url.equals("/user/admin/update/")){
//////					throw new InvalidUriException("Email,Code,PARAM MISSING");
////				 System.out.println("*****************************"+url+"******************INSIDE");
////				}
//////			 String queryString = ((HttpServletRequest)request).getQueryString();
////			}
//////		String uri = (HttpServletRequest)request).getRequestURL().toString();
////		
//	}
//
//}
