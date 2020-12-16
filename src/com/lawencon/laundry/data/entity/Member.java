package com.lawencon.laundry.data.entity;

import java.time.LocalDate;

/**
 * @author Rian Rivaldo Rumapea
 */
public class Member {

	private Long id;
	private Customer customer;
	private Membership membership;
	private LocalDate expiredAt;

	public Member(Long id) {
		this.id = id;
	}

	public Member(Customer customer, Membership membership, LocalDate expiredAt) {
		this.customer = customer;
		this.membership = membership;
		this.expiredAt = expiredAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public LocalDate getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(LocalDate expiredAt) {
		this.expiredAt = expiredAt;
	}

}
