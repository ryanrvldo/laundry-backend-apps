package com.lawencon.laundry.constant;

/**
 * @author Rian Rivaldo Rumapea
 */
public enum Roles {
	ADMINISTRATOR("U001"),
	CASHIER("U002");

	private final String code;

	Roles(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
