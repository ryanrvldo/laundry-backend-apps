package com.lawencon.laundry.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.HeaderTransactionDao;
import com.lawencon.laundry.entity.HeaderTransaction;
import com.lawencon.laundry.error.DataIsNotExistsException;
import com.lawencon.laundry.service.HeaderService;
import com.lawencon.laundry.util.ValidationUtils;

/**
 * @author Rian Rivaldo
 */
@Service
public class HeaderServiceImpl implements HeaderService {

  @Autowired
  private ValidationUtils validationUtils;

  @Autowired
  @Qualifier("hdr-trx-nq")
  private HeaderTransactionDao headerDao;

  @Override
  public void insert(HeaderTransaction header) throws Exception {
	Objects.requireNonNull(header, () -> "Header data must be submitted.");
	validationUtils.validateString(header.getReceiptNumber());
	Objects.requireNonNull(header.getStartDate(), () -> "Start date must be submitted");
	Objects.requireNonNull(header.getCustomer(), () -> "Customer data must be submitted.");
	Objects.requireNonNull(header.getUser(), () -> "User data must be submitted.");
	headerDao.insert(header);
  }

  @Override
  public List<HeaderTransaction> getAllHeader() throws Exception {
	List<HeaderTransaction> headerList = headerDao.findAll();
	if (headerList.isEmpty()) {
	  throw new NullPointerException("There is no transsaction yet.");
	}
	return headerList;
  }

  @Override
  public HeaderTransaction getHeaderByReceiptNumber(String receiptNumber) throws Exception {
	validationUtils.validateString(receiptNumber);
	return Optional.ofNullable(headerDao.findByReceiptNumber(receiptNumber))
	    .orElseThrow(() -> new DataIsNotExistsException(receiptNumber));
  }

}
