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
import com.lawencon.laundry.entity.Membership;
import com.lawencon.laundry.service.MembershipService;
import com.lawencon.laundry.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = Constants.BASE_ENDPOINT)
public class MembershipController {

  @Autowired
  private MembershipService service;

  @PostMapping(value = { "/membership" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> createRole(@RequestBody Membership requestBody) {
	try {
	  service.createMembership(requestBody);
	  return WebResponseUtils.createSuccessResponse(requestBody, HttpStatus.CREATED);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/membership" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getRoleById(@RequestParam("id") Long id) {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getMembershipById(id), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/membership/{code}" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getRoleByCode(@PathVariable("code") String code) {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getMembershipByCode(code), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/memberships" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getAllRole() {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getAll(), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @PutMapping(value = { "/membership" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> updateRole(@RequestBody Membership requestBody) {
	try {
	  service.updateMembership(requestBody);
	  return WebResponseUtils.createSuccessResponse(requestBody, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @DeleteMapping(value = { "/membership" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> deleteRole(@RequestParam("id") Long id) {
	try {
	  service.deleteMembershipById(id);
	  return WebResponseUtils.createSuccessResponse("Success membership with id: " + id, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

}
