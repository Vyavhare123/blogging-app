package com.codewithaniket.blog.playload;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	 private int id;
	 @NotEmpty(message=" Name filed is empty")
	 @Size(min =2,max = 10,message=" UserName size Should  greater than 4 character and less than 10 character")
	 private String name;
	 @Email(message="Email is not in correct format")
	 private String email;
	 @NotEmpty(message=" Password filed is empty")
	 @Size(min =3,max = 10,message=" Password size Should  greater than 4 character and less than 10 character")
	 private String password;
	 @NotEmpty(message=" About fild  filed is empty")
	 @Size(max = 100,message=" About Should not greater than 1000 character")
	 private String about;
	  
	  

}
