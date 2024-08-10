package com.aj.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aj.blog.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception)
	{
		
		String message = exception.getMessage();
		ApiResponse apiResponse=new ApiResponse(message, false);
		
		return new ResponseEntity<ApiResponse> (apiResponse, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception)
	{
		
		Map map = new HashMap<>();
		
		exception.getBindingResult().getAllErrors().forEach((error)->{
			String message = error.getDefaultMessage();
			String field = ((FieldError) error).getField();
			map.put(message, field);
		});
		
		return new ResponseEntity<Map<String,String>> (map, HttpStatus.BAD_REQUEST);
	}
}
