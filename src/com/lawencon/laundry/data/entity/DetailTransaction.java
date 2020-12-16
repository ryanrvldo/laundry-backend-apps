package com.lawencon.laundry.data.entity;

import java.math.BigDecimal;

/**
 * @author Rian Rivaldo Rumapea
 */
public class DetailTransaction {

	private Long id;
	private HeaderTransaction headerTransaction;
	private Item item;
	private Integer quantity;
	private BigDecimal itemCost;
	private LaundryType laundryType;

	public DetailTransaction() {
	}

	public DetailTransaction(Long id) {
		this.id = id;
	}

	public DetailTransaction(HeaderTransaction headerTransaction, Item item, Integer quantity, BigDecimal itemCost,
	                         LaundryType laundryType) {
		this.headerTransaction = headerTransaction;
		this.item = item;
		this.quantity = quantity;
		this.itemCost = itemCost;
		this.laundryType = laundryType;
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

	public BigDecimal getItemCost() {
		return itemCost;
	}

	public void setItemCost(BigDecimal itemCost) {
		this.itemCost = itemCost;
	}

	public LaundryType getLaundryType() {
		return laundryType;
	}

	public void setLaundryType(LaundryType laundryType) {
		this.laundryType = laundryType;
	}

}
