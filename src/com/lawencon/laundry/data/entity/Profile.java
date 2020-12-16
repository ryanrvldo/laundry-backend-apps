package com.lawencon.laundry.data.entity;

/**
 * @author Rian Rivaldo Rumapea
 */
public class Profile {

	private Long id;
	private String fullName;
	private String phone;
	private String email;
	private String address;

	public Profile(Long id) {
		this.id = id;
	}

	public Profile(String fullName, String phone, String email, String address) {
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
