package com.lawencon.laundry.entity;

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
@Table(name = "tb_m_user")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(allowSetters = true, value = { "hibernateLazyInitializer", "password" })
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "profile_id")
  private Profile profile;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id")
  private Role role;

  private Boolean isActive = true;

  public User() {
  }

  public User(Long id, String username, String password, Profile profile, Role role, Boolean isActive) {
	this.id = id;
	this.username = username;
	this.password = password;
	this.profile = profile;
	this.role = role;
	this.isActive = isActive;
  }

  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public String getUsername() {
	return username;
  }

  public void setUsername(String username) {
	this.username = username;
  }

  public String getPassword() {
	return password;
  }

  public void setPassword(String password) {
	this.password = password;
  }

  public Profile getProfile() {
	return profile;
  }

  public void setProfile(Profile profile) {
	this.profile = profile;
  }

  public Role getRole() {
	return role;
  }

  public void setRole(Role role) {
	this.role = role;
  }

  public Boolean getIsActive() {
	return isActive;
  }

  public void setIsActive(Boolean active) {
	isActive = active;
  }
}
