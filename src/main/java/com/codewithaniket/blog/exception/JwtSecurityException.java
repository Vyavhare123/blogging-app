package com.codewithaniket.blog.exception;

public class JwtSecurityException extends RuntimeException {
	
	public String massage;

	public JwtSecurityException() {
		super();
		
	}

	public JwtSecurityException(String message) {
		super(message);
		
	}
	

	

	

}
