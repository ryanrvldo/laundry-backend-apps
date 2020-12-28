package com.lawencon.laundry.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lawencon.laundry.entity.Profile;

/**
 * @author Rian Rivaldo
 */
@JsonInclude(value = Include.NON_NULL)
public class TransactionRequest {

  private String laundryType;
  private Profile customer;
  private BigDecimal totalCost;
  private Double totalWeight;
  private List<TransactionDetailRequest> items;
  private Long userId;

  public String getLaundryType() {
	return laundryType;
  }

  public void setLaundryType(String laundryType) {
	this.laundryType = laundryType;
  }

  public Profile getCustomer() {
	return customer;
  }

  public void setCustomer(Profile customer) {
	this.customer = customer;
  }

  public BigDecimal getTotalCost() {
	return totalCost;
  }

  public void setTotalCost(BigDecimal totalCost) {
	this.totalCost = totalCost;
  }

  public Double getTotalWeight() {
	return totalWeight;
  }

  public void setTotalWeight(Double totalWeight) {
	this.totalWeight = totalWeight;
  }

  public List<TransactionDetailRequest> getItems() {
	return items;
  }

  public void setItems(List<TransactionDetailRequest> items) {
	this.items = items;
  }

  public Long getUserId() {
	return userId;
  }

  public void setUserId(Long userId) {
	this.userId = userId;
  }

}
