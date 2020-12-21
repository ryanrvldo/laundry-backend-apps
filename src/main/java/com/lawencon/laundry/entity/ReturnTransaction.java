package com.lawencon.laundry.entity;

import java.time.LocalDateTime;

/**
 * @author Rian Rivaldo
 */
public class ReturnTransaction {

	private Long id;
	private DetailTransaction detailTransaction;
	private LocalDateTime returnDate;

	public ReturnTransaction() {
	}

	public ReturnTransaction(Long id, DetailTransaction detailTransaction, LocalDateTime returnDate) {
		this.id = id;
		this.detailTransaction = detailTransaction;
		this.returnDate = returnDate;
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
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

}
