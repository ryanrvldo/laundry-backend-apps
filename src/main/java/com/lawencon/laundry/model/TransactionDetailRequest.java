package com.lawencon.laundry.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo
 */
@JsonInclude(value = Include.NON_NULL)
public class TransactionDetailRequest {

  private Long id;
  private Integer quantity;

  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public Integer getQuantity() {
	return quantity;
  }

  public void setQuantity(Integer quantity) {
	this.quantity = quantity;

  }

}
