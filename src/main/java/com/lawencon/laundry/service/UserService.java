package com.lawencon.laundry.service;

import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.entity.User;

/**
 * @author Rian Rivaldo
 */
public interface UserService {

	void checkAdminIsExist() throws Exception;

	Role validateLogin(User user) throws Exception;

	Role getRole(Role role) throws Exception;

	void addNewUser(User user) throws Exception;

}
