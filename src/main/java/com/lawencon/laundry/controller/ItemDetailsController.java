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
import com.lawencon.laundry.entity.ItemDetails;
import com.lawencon.laundry.service.ItemDetailsService;
import com.lawencon.laundry.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = Constants.BASE_ENDPOINT)
public class ItemDetailsController {

  @Autowired
  private ItemDetailsService service;

  @PostMapping(value = { "/item-details" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> createRole(@RequestBody ItemDetails requestBody) {
	try {
	  service.createItemDetails(requestBody);
	  return WebResponseUtils.createSuccessResponse(requestBody, HttpStatus.CREATED);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/item-details" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getRoleById(@RequestParam("id") Long id) {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getItemDetailsById(id), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/item-details/{code}" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getRoleByCode(@PathVariable("code") String code) {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getItemDetailsByCode(code), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/item-details/all" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getAllRole() {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getAll(), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @PutMapping(value = { "/item-details" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> updateRole(@RequestBody ItemDetails requestBody) {
	try {
	  service.updateItemDetails(requestBody);
	  return WebResponseUtils.createSuccessResponse(requestBody, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @DeleteMapping(value = { "/item-details" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> deleteRole(@RequestParam("id") Long id) {
	try {
	  service.deleteItemDetailsById(id);
	  return WebResponseUtils.createSuccessResponse("Success deleted item details with id: " + id, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

}
