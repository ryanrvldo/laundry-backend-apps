package com.lawencon.laundry.entity;

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
@Table(name = "tb_r_return_transaction")
@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(allowSetters = true, value = { "hibernateLazyInitializer" })
public class ReturnTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dtl_id", nullable = false)
  private DetailTransaction detailTransaction;

  @Column(name = "returned_date")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime returnedDate;

  public ReturnTransaction() {
  }

  public ReturnTransaction(Long id, DetailTransaction detailTransaction, LocalDateTime returnDate) {
	this.id = id;
	this.detailTransaction = detailTransaction;
	this.returnedDate = returnDate;
  }

  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public DetailTransaction getDetailTransaction() {
	return detailTransaction;
  }

  public void setDetailTransaction(DetailTransaction detailTransaction) {
	this.detailTransaction = detailTransaction;
  }

  public LocalDateTime getReturnDate() {
	return returnedDate;
  }

  public void setReturnDate(LocalDateTime returnDate) {
	this.returnedDate = returnDate;
  }

}
