package com.lawencon.laundry.data.entity;

/**
 * @author Rian Rivaldo Rumapea
 */
public class User {

	private Long id;
	private String username;
	private String password;
	private Boolean isActive;
	private Profile profile;
	private Role role;

	public User(Long id) {
		this.id = id;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(Long id, String username, Boolean isActive, Profile profile, Role role) {
		this.id = id;
		this.username = username;
		this.isActive = isActive;
		this.profile = profile;
		this.role = role;
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

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
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

}
