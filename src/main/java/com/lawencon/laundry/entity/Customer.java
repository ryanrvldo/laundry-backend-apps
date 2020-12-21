package com.lawencon.laundry.entity;

import java.time.LocalDateTime;

/**
 * @author Rian Rivaldo
 */
public class Customer {

	private Long id;
	private Profile profile;
	private LocalDateTime createdDate;

	public Customer() {
	}

	public Customer(Long id, Profile profile, LocalDateTime createdDate) {
		this.id = id;
		this.profile = profile;
		this.createdDate = createdDate;
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
}
