package com.codewithaniket.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codewithaniket.blog.playload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resorceNotFoundException(ResourceNotFoundException ex){
		
		String massage=ex.getMessage();
		
		ApiResponse apiResponse=new ApiResponse(massage,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(JwtSecurityException.class)
	public ResponseEntity<String> jwtSecurityException(JwtSecurityException ex){
		String massage=ex.getMessage();
		return new ResponseEntity<String>(massage,HttpStatus.BAD_REQUEST);
	}
	
	// Hibernate relate Exception Handling

		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
			
			Map<String,String> resp=new HashMap<String,String>();
			
			ex.getBindingResult().getAllErrors().forEach((error)->{
				String fildName=((FieldError)error).getField();
				String message=error.getDefaultMessage();
				 resp.put(fildName, message);
			});
			
			
			return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
			
		}
	}	
		
		
	

	
	
	
	
	
	
	
