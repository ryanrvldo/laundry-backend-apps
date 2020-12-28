package com.lawencon.laundry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.laundry.constant.Constants;
import com.lawencon.laundry.entity.User;
import com.lawencon.laundry.service.UserService;
import com.lawencon.laundry.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = Constants.BASE_ENDPOINT)
public class UserController {

  @Autowired
  private UserService service;

  @PostMapping(value = { "/user" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> createUser(@RequestBody User requestBody) {
	try {
	  service.createUser(requestBody);
	  return WebResponseUtils.createSuccessResponse(requestBody, HttpStatus.CREATED);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/user/{username}" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getUserByUsername(username), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/user" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getUserById(@RequestParam("id") Long id) {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getUserById(id), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/users" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getAllUser() {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getAllUser(), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @PutMapping(value = { "/user" }, consumes = { Constants.APPLICATION_JSON }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> updateUser(@RequestBody User user) {
	try {
	  service.updateUser(user);
	  return WebResponseUtils.createSuccessResponse(user, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @PatchMapping(value = { "/user/status" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> updateUserActiveStatus(@RequestParam("id") Long id, @RequestParam("status") Boolean status) {
	try {
	  User user = new User();
	  user.setId(id);
	  user.setIsActive(status);
	  service.updateUserActiveStatus(user);
	  return WebResponseUtils.createSuccessResponse(user, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @PatchMapping(value = { "/user/password" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> updateUserPassword(@RequestBody User user) {
	try {
	  service.updateUserPassword(user);
	  return WebResponseUtils.createSuccessResponse(user, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @DeleteMapping(value = { "/user" })
  public ResponseEntity<?> deleteUserById(@RequestParam("id") Long requestId) {
	try {
	  service.deleteUserById(requestId);
	  return WebResponseUtils.createSuccessResponse("User has been deleted successfully.", HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

}
