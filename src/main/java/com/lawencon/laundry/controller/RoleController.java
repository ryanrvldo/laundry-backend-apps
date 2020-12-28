package com.lawencon.laundry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.laundry.constant.Constants;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.service.RoleService;
import com.lawencon.laundry.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = Constants.BASE_ENDPOINT)
public class RoleController {

  @Autowired
  private RoleService service;

  @PostMapping(value = { "/role" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> createRole(@RequestBody Role requestBody) {
	try {
	  service.createRole(requestBody);
	  return WebResponseUtils.createSuccessResponse(requestBody, HttpStatus.CREATED);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/role" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getRoleById(@RequestParam("id") Long id) {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getRoleById(id), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/role/{code}" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getRoleByCode(@PathVariable("code") String code) {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getRoleByCode(code), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/roles" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getAllRole() {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getAllRole(), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @PutMapping(value = { "/role" }, consumes = { Constants.APPLICATION_JSON }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> updateRole(@RequestBody Role requestBody) {
	try {
	  service.updateRole(requestBody);
	  return WebResponseUtils.createSuccessResponse(requestBody, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @DeleteMapping(value = { "/role" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> deleteRole(@RequestParam("code") String code) {
	try {
	  service.deleteRoleByCode(code);
	  return WebResponseUtils.createSuccessResponse("Success deleted role with code: " + code, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

}
