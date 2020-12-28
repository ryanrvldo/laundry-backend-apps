package com.lawencon.laundry.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo
 */
@Entity
@Table(name = "tb_r_hdr_transaction")
@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(allowSetters = true, value = { "hibernateLazyInitializer" })
public class HeaderTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "receipt_number", nullable = false, unique = true)
  private String receiptNumber;

  @Column(name = "start_date", nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime startDate;

  @Column(name = "total_cost")
  private BigDecimal totalCost;

  @Column(name = "total_weight")
  private Double totalWeight;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public HeaderTransaction() {
  }

  public HeaderTransaction(Long id, String receiptNumber, LocalDateTime startDate, BigDecimal totalCost,
      Double totalWeight, Customer customer, User user) {
	this.id = id;
	this.receiptNumber = receiptNumber;
	this.startDate = startDate;
	this.totalCost = totalCost;
	this.totalWeight = totalWeight;
	this.customer = customer;
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

  public Double getTotalWeight() {
	return totalWeight;
  }

  public void setTotalWeight(Double totalWeight) {
	this.totalWeight = totalWeight;
  }

  public User getUser() {
	return user;
  }

  public void setUser(User user) {
	this.user = user;
  }

}
