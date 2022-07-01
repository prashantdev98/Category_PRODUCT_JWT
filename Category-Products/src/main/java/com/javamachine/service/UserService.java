package com.javamachine.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javamachine.dto.AuthRequest;
import com.javamachine.dto.UserDto;
import com.javamachine.entity.Roles;
import com.javamachine.entity.User;
import com.javamachine.exception.UserAlreadyExistException;
import com.javamachine.exception.UserNotFoundException;
import com.javamachine.exception.UserRoleException;
import com.javamachine.repository.RoleReposiroty;
import com.javamachine.repository.UserRepository;
import com.javamachine.util.SecurityUtils;

@Transactional
@Service
public class UserService {
	
//	@Autowired
//	AuthRequest authRequest;
	
//	@Autowired
//	SecurityUtils securityUtils;
	
	@Autowired
	UserRepository userRepository;
	
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	RoleReposiroty roleReposiroty;
	
	@Autowired
	CartServices cartServices;
	
	public User saveEncryptedUser(UserDto userDto) throws UserAlreadyExistException{
		
		User saveUser = new User();
		Set<Roles> role = roleReposiroty.findByCode("ADMIN");
		System.out.println(role.toString()+"/////////////////////");
		saveUser.setUserName(userDto.getUserName());
		saveUser.setEmail(userDto.getEmail());
		saveUser.setActive(true);
		saveUser.setRoles(role);
//		saveUser.setRoles(role.getRoleName());
		saveUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
//		saveUser.setPassword(user.getPassword());
		
		return userRepository.save(saveUser);
	}
	

	public User saveNoramlUser(UserDto userDto) throws UserAlreadyExistException{
		
		User saveUser = new User();
		Set<Roles> role = roleReposiroty.findByCode("CUSTOMER");
		System.out.println(role.toString()+"/////////////////////");
		saveUser.setUserName(userDto.getUserName());
		saveUser.setEmail(userDto.getEmail());
		saveUser.setActive(true);
		saveUser.setRoles(role);
//		saveUser.setRoles(role.getRoleName());
		saveUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		saveUser = cartServices.createCartForNewUser(saveUser);
//		saveUser.setPassword(user.getPassword());
		return userRepository.save(saveUser);
	}
	
	
	public User updateUser(UserDto userDto) throws UserAlreadyExistException {
		
//		
//		if(userDto.getEmail().equals(SecurityUtils.getCurrentUsername().get())){
//			System.out.println("EQUALS++++++++++++++++++++++++++++++++++++");
//			Set<Roles> role = userRepository.findByEmail(SecurityUtils.getCurrentUsername().get()).get().getRoles();
//			User saveUser = userRepository.findByEmail(SecurityUtils.getCurrentUsername().get()).get();
////			System.out.println("THIS SECTION **************************");
//			saveUser.setUserName(userDto.getUserName());
//			saveUser.setEmail(userDto.getEmail());
//			saveUser.setActive(true);
//			saveUser.setRoles(role);
//			saveUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
//			return userRepository.save(saveUser);
//		}
//		else if(userRepository.findByEmail(userDto.getEmail()).get().isActive()) {
		if(!userRepository.existsUserByEmail(userDto.getEmail())) {
			System.out.println(SecurityUtils.getCurrentUsername()+"==================");
			Set<Roles> role = userRepository.findByEmail(SecurityUtils.getCurrentUsername().get()).get().getRoles();
			
			User saveUser = userRepository.findByEmail(SecurityUtils.getCurrentUsername().get()).get();
	//		System.out.println(role.toString()+"/////////////////////");
			saveUser.setUserName(userDto.getUserName());
			saveUser.setEmail(userDto.getEmail());
			saveUser.setActive(true);
	//		System.out.println("**************************HRRER");	
			saveUser.setRoles(role);
			saveUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
			return userRepository.save(saveUser);
		}else {
			throw new UserAlreadyExistException("User already exist change your email");
		}
	}
	
	
	
	public User updateUserByEmail(UserDto userDto,String email,String code) throws UserAlreadyExistException,UserRoleException {
		
		if(userRepository.existsUserByEmail(email)){
			
//			System.out.println("uotloop***********************************************"+roleReposiroty.existsRolesByCode(code));
			if(!roleReposiroty.existsRolesByCode(code)) {
				throw new UserRoleException("Role assigned is InValid");
			}
			
			if(!userDto.getEmail().equals(email)) {
				if(userRepository.existsUserByEmail(userDto.getEmail())) {
					throw new UserAlreadyExistException("The user email ALREADY EXIST");
				}
				
				
			}
			
			System.out.println("SAME***********************************************");
			User saveUser = userRepository.findByEmail(email).get();
			Set<Roles> role = roleReposiroty.findByCode(code.toUpperCase());
			saveUser.setUserName(userDto.getUserName());
			saveUser.setEmail(userDto.getEmail());
			saveUser.setActive(true);	
			saveUser.setRoles(role);
			saveUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
			return userRepository.save(saveUser);
		}else{
			throw new UserAlreadyExistException("The user email not found");
		}
	
		
//		if(userRepository.existsUserByEmail(userDto.getEmail())){
//			Set<Roles> role = roleReposiroty.findByCode(code.toUpperCase());
//			
//			User saveUser = userRepository.findByEmail(userDto.getEmail()).get();
////			System.out.println(role.toString()+"/////////////////////");
//			saveUser.setUserName(userDto.getUserName());
//			saveUser.setEmail(userDto.getEmail());
//			saveUser.setActive(true);
////			System.out.println("**************************HRRER");	
//			saveUser.setRoles(role);
//			saveUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
//			return userRepository.save(saveUser);
//		}else if(userRepository.existsUserByEmail(userDto.getEmail())) {
//			throw new UserAlreadyExistException("User already exist change email");
//		}else {
//			throw new UserAlreadyExistException("User already exist change email");
//		}
//		
//		System.out.println(SecurityUtils.getCurrentUsername()+"==================");
//		Set<Roles> role = roleReposiroty.findByCode(code.toUpperCase());
//		
//		User saveUser = userRepository.findByEmail(userDto.getEmail()).get();
////		System.out.println(role.toString()+"/////////////////////");
//		saveUser.setUserName(userDto.getUserName());
//		saveUser.setEmail(userDto.getEmail());
//		saveUser.setActive(true);
////		System.out.println("**************************HRRER");	
//		saveUser.setRoles(role);
//		saveUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
//		return userRepository.save(saveUser);
//		
	}
	
	public User getUserByName(String name) throws UserNotFoundException{
		if(userRepository.findByUserName(name) == null) {
			throw new  UserNotFoundException(name+" is not present");
		}
		return userRepository.findByUserName(name).get();
	}
	

	public List<Optional<User>> getUserByNameStartsWith(String name) throws UserNotFoundException{
		if(userRepository.findByUserNameStartsWith(name).isEmpty()) {
			throw new  UserNotFoundException(name+" is not present");
		}
		return userRepository.findByUserNameStartsWith(name);
		
		
	}
	

	public List<Optional<User>> getUserByNameEndsWith(String name) throws UserNotFoundException{
		
		if(userRepository.findByUserNameEndsWith(name).isEmpty()) {
			throw new  UserNotFoundException(name+" is not present");
		}
		return userRepository.findByUserNameEndsWith(name);
	}
	

	public List<Optional<User>> getUserByNameBetween(String name) throws UserNotFoundException{
		name = "%"+name+"%";
		if(userRepository.findByUserNameLike(name).isEmpty()) {
			throw new  UserNotFoundException(name+" is not present");
		}
		return userRepository.findByUserNameLike(name);
	}
	
	public String deleteUserById(int id) throws UsernameNotFoundException{
		if(userRepository.existsById(id)){
			userRepository.deleteById(id);
			return id+" is Deleted";
		}else {
			throw new UsernameNotFoundException("User is not present");
		}
	}
	 
	public String deleteByUserName(String name) {
		int status = userRepository.deleteByUserName(name);
		return status+" are DELETED";
	}
}
