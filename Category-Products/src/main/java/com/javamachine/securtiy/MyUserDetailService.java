package com.javamachine.securtiy;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javamachine.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		return new User ("foo","foo",new ArrayList<>());
//	}

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.javamachine.entity.User user = userRepository.findByUserName(username);
		return new User(user.getUserName(),user.getPassword(),new ArrayList<>());
	}
	
}
