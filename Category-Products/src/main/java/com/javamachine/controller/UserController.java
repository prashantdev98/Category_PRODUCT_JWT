package com.javamachine.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javamachine.dto.UserDto;
import com.javamachine.entity.User;
import com.javamachine.exception.UserAlreadyExistException;
import com.javamachine.exception.UserNotFoundException;
import com.javamachine.exception.UserRoleException;
import com.javamachine.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/signUp/normal")
	public ResponseEntity<?> addNormalUser(@RequestBody @Valid UserDto userDto) throws UserAlreadyExistException{
		return new ResponseEntity<> (userService.saveNoramlUser(userDto),HttpStatus.OK);
	}
	
	
	@PostMapping("/signUp")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserDto userDto) throws UserAlreadyExistException{
		return new ResponseEntity<> (userService.saveEncryptedUser(userDto),HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<?> updateUser(@RequestBody @Valid UserDto userDto) throws UserAlreadyExistException {
		return new ResponseEntity<> (userService.updateUser(userDto),HttpStatus.OK);
	}
	
	@PutMapping({"/admin/update/{email}/{code}","/admin/update/","/admin/update/{email}/","/admin/update/{code}"})
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> updateUserByEmail(@RequestBody @Valid UserDto userDto,@PathVariable Optional<String> email,@PathVariable Optional<String> code) throws UserAlreadyExistException,UserRoleException {
		return new ResponseEntity<> (userService.updateUserByEmail(userDto,email.get(),code.get()),HttpStatus.OK);
	}
	
	@GetMapping({"/admin/{name}","/admin/{name}"})
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getUserByName(@PathVariable Optional<String> name) throws  UserNotFoundException{
		return new ResponseEntity<> (userService.getUserByName(name.get()),HttpStatus.OK);
	}
	
	@GetMapping({"/admin/startWith/{name}","/admin/startWith/"})
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getUserByNameStartsWith(@PathVariable(value = "name") Optional<String> name) throws  UserNotFoundException{
		
		return new ResponseEntity<> (userService.getUserByNameStartsWith(name.get()),HttpStatus.OK);
	
	}
	
	@GetMapping(value = {"/admin/endWith/{name}","/admin/endWith/"})
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getUserByNameEndsWith(@PathVariable(required = false,name = "name") Optional<String> name) throws  UserNotFoundException{
		
		return  new ResponseEntity<>(userService.getUserByNameEndsWith(name.get()),HttpStatus.OK); 

	}
	
	@GetMapping(value = {"/admin/between/{name}","/admin/between/"})
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getUserByNameBetween(@PathVariable(name = "name") Optional<String> name) throws  UserNotFoundException{
	
		return  new ResponseEntity<>(userService.getUserByNameEndsWith(name.get()),HttpStatus.OK); 

	}
	
	
	@DeleteMapping({"/admin/delete/{id}"})
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") Optional<Integer> id)throws UserNotFoundException {
		 return new ResponseEntity<>( userService.deleteUserById(id.get()),HttpStatus.OK);
	}

	@DeleteMapping("/admin/delete/userName/{name}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deleteUserByUserName(@PathVariable(value = "name") Optional<String> userName) {
		 return new ResponseEntity<>( userService.deleteByUserName(userName.get()),HttpStatus.OK);
	}
	
	
}
