package com.lawencon.laundry.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.entity.User;
import com.lawencon.laundry.service.UserService;

/**
 * @author Rian Rivaldo
 */
@Service
public class ApiSecurityServiceImpl implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	try {
	  User userFromDb = userService.getUserByUsername(username);
	  if (userFromDb != null) {
		return new org.springframework.security.core.userdetails.User(userFromDb.getUsername(),
		    userFromDb.getPassword(),
		    Collections.emptyList());
	  }
	} catch (Exception e) {
	  e.printStackTrace();
	}
	return null;
  }

}
