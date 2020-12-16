package com.lawencon.laundry.data.entity;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LaundryType {

	private Long id;
	private String code;
	private String name;

	public LaundryType(Long id) {
		this.id = id;
	}

	public LaundryType(String code, String name) {
		this.code = code;
		this.name = name;
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
}