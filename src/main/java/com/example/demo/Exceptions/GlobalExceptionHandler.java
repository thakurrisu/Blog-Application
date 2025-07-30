package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public  ResponseEntity<String> ConstraintViolationException(ConstraintViolationException ex){
		String message = ex.getMessage();
		return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
	}
}
