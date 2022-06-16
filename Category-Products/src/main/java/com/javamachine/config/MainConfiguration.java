package com.javamachine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.javamachine.interceptor.UniversalInterceptor;

@Configuration
public class MainConfiguration implements  WebMvcConfigurer{

	@Autowired
	UniversalInterceptor universalInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor(universalInterceptor);
	}
}
