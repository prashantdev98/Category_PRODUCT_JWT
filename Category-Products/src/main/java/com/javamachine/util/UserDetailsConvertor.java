package com.javamachine.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.javamachine.entity.Roles;
import com.javamachine.entity.User;
import com.javamachine.repository.UserRepository;

public class UserDetailsConvertor implements UserDetails{

	private String userName;
    private String password;
    private boolean isActive;
    private Set<GrantedAuthority> authorities;
    
	
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public UserDetailsConvertor(User user) {
		System.out.println(user.getRoles());
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.isActive = user.isActive();
//		this.authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toSet());
		System.out.println( Arrays.toString(this.authorities.toArray())+"/*/*/*/*/*/*/*//*/**/*/*/*");
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isActive;
	}

	
}
