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
import com.lawencon.laundry.entity.Item;
import com.lawencon.laundry.service.ItemService;
import com.lawencon.laundry.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = Constants.BASE_ENDPOINT)
public class ItemController {

  @Autowired
  private ItemService service;

  @PostMapping(value = { "/item" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> createItem(@RequestBody Item item) {
	try {
	  service.createItem(item);
	  return WebResponseUtils.createSuccessResponse(item, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/item" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getItemById(@RequestParam Long id) {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getItemById(id), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/items" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getAllItem() {
	try {
	  return WebResponseUtils.createSuccessResponse(service.getAll(), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @PutMapping(value = { "/item" }, consumes = { Constants.APPLICATION_JSON }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> updateItem(@RequestBody Item requestBody) {
	try {
	  service.updateItem(requestBody);
	  return WebResponseUtils.createSuccessResponse(requestBody, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @DeleteMapping(value = { "/item" })
  public ResponseEntity<?> deleteItemById(@RequestParam Long id) {
	try {
	  service.deleteItemById(id);
	  return WebResponseUtils.createSuccessResponse("Success deleted item with id: " + id, HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

}
