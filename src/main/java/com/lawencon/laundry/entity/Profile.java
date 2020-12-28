package com.lawencon.laundry.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo
 */
@Entity
@Table(name = "tb_m_profile")
@JsonInclude(value = Include.NON_NULL)
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(length = 50, nullable = false, unique = true)
  private String phone;

  @Column(nullable = false, unique = true)
  private String email;

  private String address;

  public Profile() {
  }

  public Profile(Long id, String fullName, String phone, String email, String address) {
	this.id = id;
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
