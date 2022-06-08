package com.javamachine.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javamachine.entity.Roles;
import com.javamachine.entity.User;
import com.javamachine.repository.RoleReposiroty;
import com.javamachine.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	RoleReposiroty roleReposiroty;
	
	public User saveEncryptedUser(User user) {
		
		User saveUser = new User();
		Set<Roles> role = roleReposiroty.findByCode("ADMIN");
		System.out.println(role.toString()+"/////////////////////");
		saveUser.setUserName(user.getUserName());
		saveUser.setEmail(user.getEmail());
		saveUser.setActive(true);
		saveUser.setRoles(role);
//		saveUser.setRoles(role.getRoleName());
//		saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
		saveUser.setPassword(user.getPassword());
		return userRepository.save(saveUser);
	}
}
