package com.lawencon.laundry.entity;

/**
 * @author Rian Rivaldo
 */
public class User {

	private Long id;
	private String username;
	private String password;
	private Profile profile;
	private Role role;
	private Boolean isActive;

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
