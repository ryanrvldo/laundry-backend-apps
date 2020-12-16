package com.lawencon.laundry.data.entity;

/**
 * @author Rian Rivaldo Rumapea
 */
public class Membership {

	private Long id;
	private String code;
	private String packageName;
	private Integer discount;

	public Membership(String code, String packageName, Integer discount) {
		this.code = code;
		this.packageName = packageName;
		this.discount = discount;
	}

	public Membership(Long id) {
		this.id = id;
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

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
}
