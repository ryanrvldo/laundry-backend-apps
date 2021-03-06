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
import com.lawencon.laundry.entity.Services;
import com.lawencon.laundry.service.ItemServicesService;
import com.lawencon.laundry.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = Constants.BASE_ENDPOINT)
public class ItemServiceController {

  @Autowired
  private ItemServicesService service;

  @PostMapping(value = { "/item-service" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> createRole(@RequestBody Services requestBody) {
	try {
	  service.createService(requestBody);
	  return WebResponseUtils.createSuccessResponse(requestBody, HttpStatus.CREATED);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/item-service" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getRoleById(@RequestParam("id") Long id) {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getServiceById(id), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/item-service/{code}" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getRoleByCode(@PathVariable("code") String code) {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getServiceByCode(code), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/item-services" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getAllRole() {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getAllService(), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @PutMapping(value = { "/item-service" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> updateRole(@RequestBody Services requestBody) {
	try {
	  service.updateService(requestBody);
	  return WebResponseUtils.createSuccessResponse(requestBody, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @DeleteMapping(value = { "/item-service" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> deleteRole(@RequestParam("id") Long id) {
	try {
	  service.deleteServiceById(id);
	  return WebResponseUtils.createSuccessResponse("Success deleted item service with id: " + id, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

}
