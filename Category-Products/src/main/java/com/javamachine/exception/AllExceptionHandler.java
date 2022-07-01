package com.javamachine.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class AllExceptionHandler {

	
	@ExceptionHandler(LoginDetailInvalidException.class)
	public ResponseEntity<?> ExceptionOfLoginDetail(LoginDetailInvalidException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage",ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<?> ExceptionOfLoginDetail(HttpMessageNotReadableException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage","json format invalid");
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> ExceptionOfLoginDetail(MethodArgumentTypeMismatchException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage","check URL");
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ResponseEntity<?> ExceptionOfLoginDetail(MissingServletRequestParameterException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage","PARAMS MISSING");
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<?> ExceptionOfLoginDetail(CategoryNotFoundException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage",ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(JsonFormatNotProperException.class)
	public ResponseEntity<?> ExceptionOfLoginDetail(JsonFormatNotProperException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage",ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(JSONException.class)
	public ResponseEntity<?> ExceptionOfLoginDetail(JSONException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage",ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<?> ExceptionOfLoginDetail(UserAlreadyExistException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage",ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserRoleException.class)
	public ResponseEntity<?> ExceptionOfRole(UserRoleException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage",ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> ExceptionOfRole(UserNotFoundException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage",ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> ExceptionOfRole(NoSuchElementException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage",ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> ExceptionOfRole(ProductNotFoundException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage",ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CartEmptyException.class)
	public ResponseEntity<?> ExceptionOfRole(CartEmptyException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage",ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<?> ExceptionOfRole(CartNotFoundException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage",ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> ExceptionOfRole(AccessDeniedException ex){
		Map<String , String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage","Access Denied");
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
} 
