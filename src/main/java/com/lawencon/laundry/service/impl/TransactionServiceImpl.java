package com.lawencon.laundry.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.laundry.constant.LaundryServices;
import com.lawencon.laundry.entity.Customer;
import com.lawencon.laundry.entity.DetailTransaction;
import com.lawencon.laundry.entity.HeaderTransaction;
import com.lawencon.laundry.entity.Item;
import com.lawencon.laundry.entity.User;
import com.lawencon.laundry.error.DataAlreadyExistsException;
import com.lawencon.laundry.model.TransactionDetailRequest;
import com.lawencon.laundry.model.TransactionRequest;
import com.lawencon.laundry.model.TransactionResponse;
import com.lawencon.laundry.service.CustomerService;
import com.lawencon.laundry.service.DetailService;
import com.lawencon.laundry.service.HeaderService;
import com.lawencon.laundry.service.ItemService;
import com.lawencon.laundry.service.TransactionService;
import com.lawencon.laundry.service.UserService;
import com.lawencon.laundry.util.ValidationUtils;

/**
 * @author Rian Rivaldo
 */
@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  private ValidationUtils validationUtils;

  @Autowired
  private UserService userService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private ItemService itemService;

  @Autowired
  private HeaderService headerService;

  @Autowired
  private DetailService detailService;

  @Transactional
  @Override
  public String addTransaction(String data) throws Exception {
	ObjectMapper objMapper = new ObjectMapper();
	TransactionRequest request = objMapper.readValue(data.getBytes(), TransactionRequest.class);
	if (!request.getLaundryType().equalsIgnoreCase(LaundryServices.KILOS_LAUNDRY.getCode())
	    && !request.getLaundryType().equalsIgnoreCase(LaundryServices.UNIT_LAUNDRY.getCode())) {
	  throw new IllegalArgumentException("Invalid laundry type. Only UL (Unit Laundry) and KL (Kilos Laundry).");
	}
	HeaderTransaction header = new HeaderTransaction();
	User user = userService.getUserById(request.getUserId());
	header.setUser(user);

	String receiptNumber = "TRX" + System.currentTimeMillis();
	header.setReceiptNumber(receiptNumber);
	LocalDateTime dateTimeNow = LocalDateTime.now();
	header.setStartDate(dateTimeNow);
	if (request.getLaundryType().equalsIgnoreCase(LaundryServices.KILOS_LAUNDRY.getCode())) {
	  header.setTotalCost(Optional.ofNullable(request.getTotalCost())
	      .orElseThrow(() -> new IllegalArgumentException("Kilos laundry must be input total cost.")));
	  header.setTotalWeight(Optional.ofNullable(request.getTotalWeight())
	      .orElseThrow(() -> new IllegalArgumentException("Kilos laundry must be input total weight.")));
	} else {
	  header.setTotalWeight(0D);
	  header.setTotalCost(BigDecimal.ZERO);
	}

	Customer customer = new Customer();
	customer.setProfile(request.getCustomer());
	try {
	  customerService.createCustomer(customer);
	  header.setCustomer(customer);
	} catch (DataAlreadyExistsException e) {
	  Customer prevCustomer = customerService.getCustomerByProfileId(customer.getProfile().getId());
	  header.setCustomer(prevCustomer);
	}

	BigDecimal totalCost = BigDecimal.ZERO;
	List<DetailTransaction> detailTransactions = new ArrayList<>();
	for (TransactionDetailRequest reqItem : request.getItems()) {
	  DetailTransaction detailTransaction = new DetailTransaction();
	  detailTransaction.setQuantity(reqItem.getQuantity());

	  Item item = itemService.getItemById(reqItem.getId());
	  detailTransaction.setItem(item);
	  detailTransaction.setFinishDate(dateTimeNow.plusHours(item.getServices().getHourDuration()));

	  detailTransactions.add(detailTransaction);
	  totalCost = totalCost.add(item.getCost().multiply(BigDecimal.valueOf(reqItem.getQuantity())));
	}
	if (request.getLaundryType().equalsIgnoreCase(LaundryServices.UNIT_LAUNDRY.getCode())) {
	  header.setTotalCost(totalCost);
	}

	headerService.insert(header);
	for (DetailTransaction detail : detailTransactions) {
	  detail.setHeaderTransaction(header);
	  detailService.createDetail(detail);
	}

	return receiptNumber;
  }

  @Override
  public List<TransactionResponse> getAllTransaction() throws Exception {
	List<HeaderTransaction> headerList = headerService.getAllHeader();
	List<TransactionResponse> responseList = new ArrayList<>();
	for (HeaderTransaction header : headerList) {
	  TransactionResponse response = getTransactionByReceiptNumber(header.getReceiptNumber());
	  responseList.add(response);
	}
	return responseList;
  }

  @Override
  public TransactionResponse getTransactionByReceiptNumber(String receiptNumber) throws Exception {
	HeaderTransaction header = validationUtils.validateGet(() -> headerService.getHeaderByReceiptNumber(receiptNumber));
	Objects.requireNonNull(header, () -> "Invalid receipt number.");
	List<DetailTransaction> details = detailService.getAllDetailByHeaderId(header.getId());
	Customer customer = customerService.getCustomerById(header.getCustomer().getId());

	TransactionResponse response = new TransactionResponse();
	response.setReceiptNumber(header.getReceiptNumber());
	response.setCustomer(customer.getProfile());
	List<Item> itemList = new ArrayList<>();
	for (DetailTransaction detail : details) {
	  Item item = itemService.getItemById(detail.getItem().getId());
	  itemList.add(item);
	}
	response.setItems(itemList);
	return response;
  }

}
