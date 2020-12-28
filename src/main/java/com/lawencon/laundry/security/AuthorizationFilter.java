package com.lawencon.laundry.security;

import java.io.IOException;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.lawencon.laundry.constant.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * @author Rian Rivaldo
 */

public class AuthorizationFilter extends BasicAuthenticationFilter {

  public AuthorizationFilter(AuthenticationManager authManager) {
	super(authManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
	String header = request.getHeader("Authorization");
	if (header == null || header.isEmpty() || !header.startsWith("Bearer")) {
	  response.setStatus(HttpStatus.UNAUTHORIZED.value());
	  return;
	}

	String secretKey = "JDJhJDEwJFNQSXhydGhIeS56RmdiaWlJRmVlWnVvSHVqai50WVJqV1RHWGhnUzI3eS5xSjdLa1p6enNp";
	SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
	String username = null;
	try {
	  String bodyToken = header.replaceFirst("Bearer ", "");
	  username = Jwts.parserBuilder()
	      .setSigningKey(key)
	      .build()
	      .parseClaimsJws(bodyToken)
	      .getBody()
	      .getSubject();
	} catch (Exception e) {
	  e.printStackTrace();
	  response.setStatus(HttpStatus.UNAUTHORIZED.value());
	  return;
	}

	Authentication auth = new UsernamePasswordAuthenticationToken(username, null, null);
	SecurityContextHolder.getContext().setAuthentication(auth);
	chain.doFilter(request, response);
  }

  @Override
  protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException {
	response.setContentType(Constants.APPLICATION_JSON);
	response.setStatus(HttpStatus.UNAUTHORIZED.value());
	response.getWriter().format("{%n\t\"message\": \"%s\"%n}", "invalid token.");
  }

}
