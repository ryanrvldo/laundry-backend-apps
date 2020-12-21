package com.lawencon.laundry.entity;

import java.time.LocalDateTime;

/**
 * @author Rian Rivaldo
 */
public class Member {

	private Long id;
	private Customer customer;
	private Membership membership;
	private LocalDateTime startDate;
	private LocalDateTime expiredDate;

	public Member() {
	}

	public Member(Long id, Customer customer, Membership membership, LocalDateTime startDate, LocalDateTime expiredDate) {
		this.id = id;
		this.customer = customer;
		this.membership = membership;
		this.startDate = startDate;
		this.expiredDate = expiredDate;
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDateTime expiredDate) {
		this.expiredDate = expiredDate;
	}

}
