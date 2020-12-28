package com.lawencon.laundry.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo
 */
@Entity
@Table(name = "tb_m_member")
@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(allowSetters = true, value = { "hibernateLazyInitializer" })
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false, unique = true)
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "membership_id", nullable = false)
  private Membership membership;

  @Column(name = "start_date", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "expired_date", nullable = false)
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
