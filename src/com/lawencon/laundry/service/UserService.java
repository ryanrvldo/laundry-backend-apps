package com.lawencon.laundry.service;

import com.lawencon.laundry.data.entity.Role;
import com.lawencon.laundry.data.entity.User;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface UserService {

	Role getRole(Role role) throws Exception;

	void addNewUser(User user) throws Exception;

	User validateLogin(User user) throws Exception;

}
