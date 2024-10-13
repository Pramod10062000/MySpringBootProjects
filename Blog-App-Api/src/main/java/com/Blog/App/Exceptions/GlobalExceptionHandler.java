package com.Blog.App.Exceptions;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Blog.App.Entites.UserEntity;
import com.Blog.App.Payloads.ApiResponse;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException (MethodArgumentNotValidException ex){
		Map<String,String> resp=new HashMap<String,String>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String mess=error.getDefaultMessage();
			resp.put(fieldName, mess);
		});
	System.out.println(resp);
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExeption(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiresp=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiresp,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<ApiResponse> InvalidIdException(InvalidIdException ex){
		String message =ex.getMessage();
		ApiResponse apiresp =new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiresp,HttpStatus.NOT_FOUND	);
	}
	
	@ExceptionHandler(ApiExceptionHandling.class)
	public ResponseEntity<ApiResponse> ApiExceptionHandling(ApiExceptionHandling ex){
		String message = ex.getMessage();
		ApiResponse apiresp=new ApiResponse(message,true);
		return new ResponseEntity<ApiResponse>(apiresp,HttpStatus.BAD_REQUEST);
	}
	
	
}
