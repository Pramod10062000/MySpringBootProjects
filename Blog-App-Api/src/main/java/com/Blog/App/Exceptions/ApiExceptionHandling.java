package com.Blog.App.Exceptions;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Data;


public class ApiExceptionHandling extends RuntimeException {
	
	public ApiExceptionHandling(String message) {
		super(message);
		
	}

}
