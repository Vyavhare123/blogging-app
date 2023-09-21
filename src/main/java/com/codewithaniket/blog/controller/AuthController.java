package com.codewithaniket.blog.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithaniket.blog.playload.JwtAuthRequest;
import com.codewithaniket.blog.playload.JwtAuthResponse;
import com.codewithaniket.blog.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest){
		
		this.authenticate(jwtAuthRequest.getUserName(),jwtAuthRequest.getPassword());
		
		UserDetails UserDetail = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUserName());
		String token = this.jwtTokenHelper.generateToken(UserDetail);
		
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		return new  ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		
		
		
		
		
	}

	private void authenticate(String userName, String password) {
		
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userName, password);
		this.authenticationManager.authenticate(authenticationToken);
		
	}

}
