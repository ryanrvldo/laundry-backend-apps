package com.lawencon.laundry.data.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Rian Rivaldo Rumapea
 */
public class HeaderTransaction {

	private Long id;
	private String receiptNumber;
	private Customer customer;
	private LocalDateTime startedAt;
	private LocalDateTime finishedAt;
	private BigDecimal totalCost;
	private Double totalWeight;
	private User user;

	public HeaderTransaction() {
	}

	public HeaderTransaction(Long id) {
		this.id = id;
	}

	public HeaderTransaction(String receiptNumber, Customer customer, LocalDateTime startedAt, LocalDateTime finishedAt,
	                         BigDecimal totalCost, Double totalWeight) {
		this.receiptNumber = receiptNumber;
		this.customer = customer;
		this.startedAt = startedAt;
		this.finishedAt = finishedAt;
		this.totalCost = totalCost;
		this.totalWeight = totalWeight;
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

	public LocalDateTime getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}

	public LocalDateTime getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(LocalDateTime finishedAt) {
		this.finishedAt = finishedAt;
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
