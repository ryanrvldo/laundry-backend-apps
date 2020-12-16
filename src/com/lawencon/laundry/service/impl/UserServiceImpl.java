package com.lawencon.laundry.service.impl;

import com.lawencon.laundry.data.entity.Profile;
import com.lawencon.laundry.data.entity.Role;
import com.lawencon.laundry.data.entity.User;
import com.lawencon.laundry.data.repository.ProfileRepository;
import com.lawencon.laundry.data.repository.RoleRepository;
import com.lawencon.laundry.data.repository.UserRepository;
import com.lawencon.laundry.data.repository.impl.ProfileRepositoryImpl;
import com.lawencon.laundry.data.repository.impl.RoleRepositoryImpl;
import com.lawencon.laundry.data.repository.impl.UserRepositoryImpl;
import com.lawencon.laundry.service.UserService;
import com.lawencon.laundry.util.UserSessionCache;

import javax.security.auth.login.FailedLoginException;
import java.util.Optional;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserServiceImpl implements UserService {

	private final RoleRepository roleRepository = new RoleRepositoryImpl();
	private final ProfileRepository profileRepository = new ProfileRepositoryImpl();
	private final UserRepository userRepository = new UserRepositoryImpl();

	@Override
	public Role getRole(Role role) throws Exception {
		Optional.ofNullable(role.getCode())
				.orElseThrow(() -> new NullPointerException("Role code must be not empty."));
		return Optional.ofNullable(roleRepository.get(role))
				.orElseThrow(() -> new IllegalArgumentException("Invalid code role."));
	}

	@Override
	public void addNewUser(User user) throws Exception {
		if (user.getUsername() == null || user.getPassword() == null) {
			throw new NullPointerException("Username or password must be not empty.");
		} else if (user.getProfile().getFullName() == null || user.getProfile().getPhone() == null) {
			throw new NullPointerException("User full name and phone must be not empty.");
		} else if (user.getProfile().getEmail() == null) {
			user.getProfile().setEmail("");
		} else if (user.getProfile().getAddress() == null) {
			user.getProfile().setAddress("");
		}

		Profile profile = Optional.ofNullable(profileRepository.add(user.getProfile()))
				.orElseThrow(() -> new Exception("Failed to add user profile to database."));
		user.setProfile(profile);

		Optional.ofNullable(userRepository.add(user))
				.orElseThrow(() -> new Exception("Failed to add new user to database."));
	}

	@Override
	public User validateLogin(User user) throws Exception {
		if (user.getUsername() == null || user.getPassword() == null) {
			throw new NullPointerException("Your username and password must be not empty. Please try again.");
		}

		User validatedUser = Optional.ofNullable(userRepository.get(user))
				.orElseThrow(() -> new FailedLoginException("Invalid username or password."));
		UserSessionCache userSessionCache = UserSessionCache.getInstance();
		userSessionCache.setActiveUser(validatedUser);

		return validatedUser;
	}

}
