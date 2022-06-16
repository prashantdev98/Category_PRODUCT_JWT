package com.javamachine.securtiy;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javamachine.repository.UserRepository;
import com.javamachine.util.UserDetailsConvertor;

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
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<com.javamachine.entity.User> user = userRepository.findByEmail(email);
		System.out.println(email+"//************************************"+user.get().getPassword());
//		return new User(user.getUserName(),user.getPassword(),new ArrayList<>());
		return user.map(UserDetailsConvertor::new).orElseThrow(()-> new UsernameNotFoundException(email+" Does not Exist"));
	}
	
}
