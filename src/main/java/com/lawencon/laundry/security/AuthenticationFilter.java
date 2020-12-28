package com.lawencon.laundry.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.laundry.constant.Constants;
import com.lawencon.laundry.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * @author Rian Rivaldo
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authManager;

  public AuthenticationFilter(AuthenticationManager authManager) {
	this.authManager = authManager;
	super.setFilterProcessesUrl("/api/authentication");
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
	User user = new User();
	try {
	  user = new ObjectMapper().readValue(request.getInputStream(), User.class);
	} catch (Exception e) {
	  e.printStackTrace();
	}
	return authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
	String secretKey = "JDJhJDEwJFNQSXhydGhIeS56RmdiaWlJRmVlWnVvSHVqai50WVJqV1RHWGhnUzI3eS5xSjdLa1p6enNp";
	SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
	String token = Jwts.builder()
	    .signWith(key)
	    .setSubject(authResult.getName())
	    .setExpiration(Date.from(
	        LocalDateTime.now()
	            .plusHours(6)
	            .atZone(ZoneId.systemDefault())
	            .toInstant()))
	    .compact();

	response.setContentType(Constants.APPLICATION_JSON);
	response.getWriter().format("{%n\t\"token\": \"%s\"%n}", token);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {
	response.setStatus(HttpStatus.UNAUTHORIZED.value());
	response.getWriter().format("{%n\t\"message\": \"%s\"%n}", "Invalid user.");
  }

}
