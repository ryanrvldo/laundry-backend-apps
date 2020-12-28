package com.lawencon.laundry.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.UserDao;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.entity.User;
import com.lawencon.laundry.error.DataAlreadyExistsException;
import com.lawencon.laundry.error.DataIsNotExistsException;
import com.lawencon.laundry.service.ProfileService;
import com.lawencon.laundry.service.RoleService;
import com.lawencon.laundry.service.UserService;
import com.lawencon.laundry.util.ValidationUtils;

/**
 * @author Rian Rivaldo
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private ValidationUtils validationUtils;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Autowired
  @Qualifier("user-jpa")
  private UserDao userDao;

  @Autowired
  private RoleService roleService;

  @Autowired
  private ProfileService profileService;

  @Transactional
  @Override
  public void createUser(User user) throws Exception {
	if (validateUser(user) != null) {
	  throw new DataAlreadyExistsException(user.getUsername());
	}

	profileService.createProfile(user.getProfile());
	Role validatedRole = roleService
	    .getRoleByCode(Objects.requireNonNull(user.getRole().getCode(), () -> "Role code must be submitted."));
	user.setRole(validatedRole);
	String hash = encoder.encode(user.getPassword());
	user.setPassword(hash);
	userDao.insert(user);
  }

  @Override
  public User getUserByUsername(String username) throws Exception {
	validationUtils.validateString(username);
	return Optional.ofNullable(validationUtils.validateGet(() -> userDao.findByUsername(username)))
	    .orElseThrow(() -> new DataIsNotExistsException(username));
  }

  @Override
  public User getUserById(Long id) throws Exception {
	return validationUtils.validateEntityId(id, () -> userDao.findById(id));
  }

  @Override
  public List<User> getAllUser() throws Exception {
	List<User> userList = userDao.findAll();
	if (userList.isEmpty()) {
	  throw new NullPointerException("Users data is empty.");
	}
	return userList;
  }

  @Transactional
  @Override
  public void updateUser(User user) throws Exception {
	Optional.ofNullable(validateUser(user))
	    .orElseThrow(() -> new DataIsNotExistsException(user.getUsername()));
	Profile profile = user.getProfile();
	profileService.updateProfile(profile);
	Role role = user.getRole();
	roleService.updateRole(role);

	userDao.update(user);
  }

  @Transactional
  @Override
  public void updateUserActiveStatus(User user) throws Exception {
	Objects.requireNonNull(user, () -> "User data must be submitted.");
	Objects.requireNonNull(user.getIsActive(), () -> "Active status must be submitted.");
	User validatedUser = validationUtils.validateEntityId(user.getId(), () -> userDao.findById(user.getId()));
	user.setUsername(validatedUser.getUsername());
	if (validatedUser.getIsActive().equals(user.getIsActive())) {
	  throw new IllegalArgumentException("New active status of user with id " + user.getId() + " is same as before.");
	}
	userDao.updateActiveStatus(user);
  }

  @Transactional
  @Override
  public void updateUserPassword(User user) throws Exception {
	Objects.requireNonNull(user, () -> "User data must be submitted.");
	Objects.requireNonNull(user.getPassword(), () -> "New password must be submitted.");
	User validatedUser = validationUtils.validateEntityId(user.getId(), () -> userDao.findById(user.getId()));
	user.setUsername(validatedUser.getUsername());
	String hash = encoder.encode(user.getPassword());
	user.setPassword(hash);
	userDao.updatePassword(user);
  }

  @Transactional
  @Override
  public void deleteUserById(Long id) throws Exception {
	User user = validationUtils.validateEntityId(id, () -> userDao.findById(id));
	userDao.delete(user);
	profileService.deleteProfileById(user.getProfile().getId());
  }

  private User validateUser(User user) throws Exception {
	Objects.requireNonNull(user, () -> "User data must be submitted.");
	validationUtils.validateString(user.getUsername(), user.getPassword());
	Objects.requireNonNull(user.getProfile(), () -> "Profile data must be submitted.");
	Objects.requireNonNull(user.getRole(), () -> "Role data must be submitted.");
	return validationUtils.validateGet(() -> userDao.findByUsername(user.getUsername()));
  }

}
