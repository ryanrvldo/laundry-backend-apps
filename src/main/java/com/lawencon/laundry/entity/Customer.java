package com.lawencon.laundry.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo
 */
@Entity
@Table(name = "tb_m_customer")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(allowSetters = true, value = { "hibernateLazyInitializer" })
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "profile_id")
  private Profile profile;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
