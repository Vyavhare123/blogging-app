package com.codewithaniket.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codewithaniket.blog.exception.JwtSecurityException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
		return new AntPathMatcher().match("/api/v1/auth/login", request.getServletPath());
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestToken = request.getHeader("Authorization");

		System.out.println(requestToken);

		String userName = null;
		String token = null;

		if (requestToken != null && requestToken.startsWith("Bearer")) {

			token = requestToken.substring(7);
			try {
				userName = this.jwtTokenHelper.extractUsername(token);

			} catch (IllegalArgumentException e) {

				System.out.println("Unable to get jwt token");

			} catch (ExpiredJwtException e) {
				System.out.println("Jwt token has expired");

			} catch (MalformedJwtException e) {
				System.out.println("Invalid Exception");
			}

		} else {

			throw new JwtSecurityException("Jwt token is not begin with Bearer");

		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails UserDetails = this.userDetailsService.loadUserByUsername(userName);

			if (this.jwtTokenHelper.validateToken(token, UserDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(UserDetails,null,UserDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			} else {

				throw new JwtSecurityException("Invalid Jwt Token");

			}

		} else {
			
			throw new JwtSecurityException("Username is null or context is not null");

		}
		
		filterChain.doFilter(request, response);

	}

}
