package com.lawencon.laundry.util;

import com.lawencon.laundry.entity.User;

/**
 * @author Rian Rivaldo
 */
public class UserSessionCache {

	private User activeUser;

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

}
