package com.codewithaniket.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	
	String resourseName;
	String fieldName;
	long fieldValue;
	String fildname;
	
	
	public ResourceNotFoundException(String resourseName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s",resourseName,fieldName,fieldValue)); 
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public ResourceNotFoundException(String resourseName, String fieldName, String  fildname) {
		super(String.format("%s not found with %s : %s",resourseName,fieldName, fildname)); 
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.fildname = fildname;
	}

}
