package com.lawencon.laundry.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Rian Rivaldo
 */
public class HeaderTransaction {

	private Long id;
	private String receiptNumber;
	private Customer customer;
	private LocalDateTime startDate;
	private BigDecimal totalCost;
	private Double totalWeight;
	private User user;

	public HeaderTransaction() {
	}

	public HeaderTransaction(Long id, String receiptNumber, Customer customer, LocalDateTime startDate,
	                         BigDecimal totalCost, Double totalWeight, User user) {
		this.id = id;
		this.receiptNumber = receiptNumber;
		this.customer = customer;
		this.startDate = startDate;
		this.totalCost = totalCost;
		this.totalWeight = totalWeight;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

}
