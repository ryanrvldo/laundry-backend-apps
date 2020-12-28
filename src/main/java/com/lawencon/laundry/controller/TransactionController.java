package com.lawencon.laundry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.laundry.constant.Constants;
import com.lawencon.laundry.service.TransactionService;
import com.lawencon.laundry.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = Constants.BASE_ENDPOINT)
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @PostMapping(value = "/transaction", consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  private ResponseEntity<?> createRentalTransaction(@RequestBody String body) {
	try {
	  String receiptNumber = this.transactionService.addTransaction(body);
	  return WebResponseUtils.createSuccessResponse("Success added transaction with receipt number: " + receiptNumber,
	      HttpStatus.CREATED);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/transaction/{receipt}" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getTransaction(@PathVariable("receipt") String receipt) {
	try {
	  return WebResponseUtils.createSuccessResponse(transactionService.getTransactionByReceiptNumber(receipt),
	      HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

  @GetMapping(value = { "/transactions" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getAllTransaction() {
	try {
	  return WebResponseUtils.createSuccessResponse(transactionService.getAllTransaction(), HttpStatus.OK);
	} catch (Exception e) {
	  return WebResponseUtils.createErrorResponse(e);
	}
  }

}
