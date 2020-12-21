package com.lawencon.laundry.constant;

/**
 * @author Rian Rivaldo
 */
public enum LaundryServices {
	UNIT_LAUNDRY("UL"),
	KILOS_LAUNDRY("KL");

	String code;

	LaundryServices(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
