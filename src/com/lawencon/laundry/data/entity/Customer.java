package com.lawencon.laundry.data.entity;

/**
 * @author Rian Rivaldo Rumapea
 */
public class Customer {

	private Long id;
	private Profile profile;

	public Customer() {
	}

	public Customer(Long id, Profile profile) {
		this.id = id;
		this.profile = profile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
