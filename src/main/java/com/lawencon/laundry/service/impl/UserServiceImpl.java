package com.lawencon.laundry.service.impl;

import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.entity.User;
import com.lawencon.laundry.repository.ProfileRepository;
import com.lawencon.laundry.repository.RoleRepository;
import com.lawencon.laundry.repository.UserRepository;
import com.lawencon.laundry.service.UserService;
import com.lawencon.laundry.util.EncryptionUtils;
import com.lawencon.laundry.util.UserSessionCache;
import org.springframework.transaction.support.TransactionTemplate;

import javax.security.auth.login.FailedLoginException;
import java.util.List;
import java.util.Optional;

/**
 * @author Rian Rivaldo
 */
public class UserServiceImpl implements UserService {

	private final TransactionTemplate transactionTemplate;
	private final UserSessionCache userSessionCache;
	private final RoleRepository roleRepository;
	private final ProfileRepository profileRepository;
	private final UserRepository userRepository;

	public UserServiceImpl(TransactionTemplate transactionTemplate, UserSessionCache userSessionCache, RoleRepository roleRepository, ProfileRepository profileRepository,
	                       UserRepository userRepository) {
		this.transactionTemplate = transactionTemplate;
		this.userSessionCache = userSessionCache;
		this.roleRepository = roleRepository;
		this.profileRepository = profileRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void checkAdminIsExist() throws Exception {
		List<User> userList = transactionTemplate.execute(val -> {
			try {
				return userRepository.getAll();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
		if (userList != null && userList.isEmpty()) {
			throw new Exception("There is no user yet. Please add first.");
		}
	}

	@Override
	public Role validateLogin(User user) throws Exception {
		if (user.getUsername() == null || user.getPassword() == null) {
			throw new NullPointerException("Your username and password must be not empty. Please try again.");
		}

		String encryptedPassword = EncryptionUtils.encrypt(user.getPassword());
		user.setPassword(encryptedPassword);

		User validatedUser = transactionTemplate.execute(val -> {
			try {
				return userRepository.get(user);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
		Optional.ofNullable(validatedUser)
				.orElseThrow(() -> new FailedLoginException("Invalid username or password."));
		validatedUser.setPassword(null);
		userSessionCache.setActiveUser(validatedUser);
		return validatedUser.getRole();
	}

	@Override
	public Role getRole(Role role) throws Exception {
		Optional.ofNullable(role.getCode())
				.orElseThrow(() -> new Exception("Role code must be not empty."));
		return transactionTemplate.execute(val -> {
			try {
				return Optional.ofNullable(roleRepository.get(role))
						.orElseThrow(() -> new IllegalArgumentException("Invalid code role."));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
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

		String encryptedPassword = EncryptionUtils.encrypt(user.getPassword());
		user.setPassword(encryptedPassword);
		transactionTemplate.executeWithoutResult(val -> {
			Profile profile = user.getProfile();
			try {
				profileRepository.add(profile);
				user.setProfile(profile);

				userRepository.add(user);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
	}

}
