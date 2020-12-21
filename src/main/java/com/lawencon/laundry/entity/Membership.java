package com.lawencon.laundry.entity;

/**
 * @author Rian Rivaldo
 */
public class Membership {

	private Long id;
	private String code;
	private String name;
	private Double discount;

	public Membership() {
	}

	public Membership(Long id, String code, String name, Double discount) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.discount = discount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
}
