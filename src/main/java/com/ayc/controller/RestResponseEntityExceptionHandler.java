package com.ayc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ayc.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
	private static final String RESOURCE_NOT_FOUND = "Resource NotFound";
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<String> handleNotFoundException(Exception ex, WebRequest request){
		return new ResponseEntity<String>(RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
	}

}
