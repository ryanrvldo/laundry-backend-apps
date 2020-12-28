package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.TransactionResponse;

/**
 * @author Rian Rivaldo
 */
public interface TransactionService {

  String addTransaction(String data) throws Exception;

  TransactionResponse getTransactionByReceiptNumber(String receiptNumber) throws Exception;

  List<TransactionResponse> getAllTransaction() throws Exception;

}
