package com.lawencon.laundry.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lawencon.laundry.entity.Item;
import com.lawencon.laundry.entity.Profile;

/**
 * @author Rian Rivaldo
 */
@JsonInclude(value = Include.NON_NULL)
public class TransactionResponse {

  private String receiptNumber;
  private Profile customer;
  private List<Item> items;

  public String getReceiptNumber() {
	return receiptNumber;
  }

  public void setReceiptNumber(String receiptNumber) {
	this.receiptNumber = receiptNumber;
  }

  public Profile getCustomer() {
	return customer;
  }

  public void setCustomer(Profile customer) {
	this.customer = customer;
  }

  public List<Item> getItems() {
	return items;
  }

  public void setItems(List<Item> items) {
	this.items = items;
  }

}
