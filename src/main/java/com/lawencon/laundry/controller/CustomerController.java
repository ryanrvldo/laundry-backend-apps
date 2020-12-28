package com.lawencon.laundry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.laundry.constant.Constants;
import com.lawencon.laundry.entity.Customer;
import com.lawencon.laundry.service.CustomerService;
import com.lawencon.laundry.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = Constants.BASE_ENDPOINT)
public class CustomerController {

  @Autowired
  private CustomerService service;

  @PostMapping(value = { "/customer" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> createCustomer(@RequestBody Customer requestBody) {
	try {
	  service.createCustomer(requestBody);
	  return WebResponseUtils.createSuccessResponse(requestBody, HttpStatus.CREATED);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/customer" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getCustomerById(@RequestParam("id") Long id) {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getCustomerById(id), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/customers" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getAllCustomer() {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getAllCustomer(), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @PutMapping(value = { "/customer" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> updateCustomer(@RequestBody Customer requestBody) {
	try {
	  service.updateCustomer(requestBody);
	  return WebResponseUtils.createSuccessResponse(requestBody, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @DeleteMapping(value = { "/customer" })
  public ResponseEntity<?> deleteCustomerById(@RequestParam("id") Long requestId) {
	try {
	  service.deleteCustomerById(requestId);
	  return WebResponseUtils.createSuccessResponse("Customer has been deleted successfully.", HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

}
