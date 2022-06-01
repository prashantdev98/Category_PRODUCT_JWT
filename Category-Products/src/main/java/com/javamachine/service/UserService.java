package com.javamachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javamachine.entity.User;
import com.javamachine.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public User saveEncryptedUser(User user) {
		User saveUser = new User();
		saveUser.setUserName(user.getUserName());
		saveUser.setEmail(user.getEmail());
		saveUser.setActive(true);
		saveUser.setRoles("ADMIN");
		saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(saveUser);
	}
}
