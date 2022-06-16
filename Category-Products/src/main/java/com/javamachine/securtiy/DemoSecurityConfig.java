package com.javamachine.securtiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.javamachine.filter.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private MyUserDetailService myUserDetailService; 
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth )throws Exception{	
		auth.userDetailsService(myUserDetailService);
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		 http.csrf().disable().authorizeHttpRequests().antMatchers("/authenticate").permitAll().anyRequest().authenticated().and().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/authenticate","/user/login","/user/signUp","/user/signUp/normal").permitAll().and().authorizeRequests().antMatchers("/category/**","/products/**").authenticated().and().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
	}
	

	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		return encode;
	}

	
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
//  
//    @Bean
//    public BCryptPasswordEncoder passwordEncoderE() {
//    	return new BCryptPasswordEncoder();
//    }
}
