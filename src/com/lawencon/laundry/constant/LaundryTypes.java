package com.lawencon.laundry.constant;

/**
 * @author Rian Rivaldo Rumapea
 */
public enum LaundryTypes {
	UNIT_LAUNDRY("UL"),
	KILOS_LAUNDRY("KL");

	String code;

	LaundryTypes(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
