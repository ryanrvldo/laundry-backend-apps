package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.entity.HeaderTransaction;

/**
 * @author Rian Rivaldo
 */

public interface HeaderService {

  void insert(HeaderTransaction header) throws Exception;

  List<HeaderTransaction> getAllHeader() throws Exception;

  HeaderTransaction getHeaderByReceiptNumber(String receiptNumber) throws Exception;

}
