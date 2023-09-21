package com.codewithaniket.blog.service;

import java.util.List;

import com.codewithaniket.blog.entity.User;
import com.codewithaniket.blog.playload.UserDto;

public interface UserService {
	
	 UserDto createUser( UserDto user);
	 
	 UserDto updateUser(UserDto user,Integer userId);
	 
	 UserDto getUserById (Integer userId);
	 
	 List<UserDto>getAllUser();
	 
	 void deleteUser(Integer userId);

}
