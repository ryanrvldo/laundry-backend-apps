package com.lawencon.laundry.entity;

import java.time.LocalDateTime;

/**
 * @author Rian Rivaldo
 */
public class DetailTransaction {

	private Long id;
	private HeaderTransaction headerTransaction;
	private Item item;
	private Integer quantity;
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
