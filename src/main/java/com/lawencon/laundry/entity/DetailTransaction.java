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
@Table(name = "tb_r_dtl_transaction")
@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(allowSetters = true, value = { "hibernateLazyInitializer" })
public class DetailTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hdr_id", nullable = false)
  private HeaderTransaction headerTransaction;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  @Column(nullable = false)
  private Integer quantity;

  @Column(name = "finish_date", nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime finishDate;

  public DetailTransaction() {
  }

  public DetailTransaction(Long id, HeaderTransaction headerTransaction, Item item, Integer quantity,
      LocalDateTime finishDate) {
	this.id = id;
	this.headerTransaction = headerTransaction;
	this.item = item;
	this.quantity = quantity;
	this.finishDate = finishDate;
  }

  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public HeaderTransaction getHeaderTransaction() {
	return headerTransaction;
  }

  public void setHeaderTransaction(HeaderTransaction headerTransaction) {
	this.headerTransaction = headerTransaction;
  }

  public Item getItem() {
	return item;
  }

  public void setItem(Item item) {
	this.item = item;
  }

  public Integer getQuantity() {
	return quantity;
  }

  public void setQuantity(Integer quantity) {
	this.quantity = quantity;
  }

  public LocalDateTime getFinishDate() {
	return finishDate;
  }

  public void setFinishDate(LocalDateTime finishDate) {
	this.finishDate = finishDate;
  }

}
