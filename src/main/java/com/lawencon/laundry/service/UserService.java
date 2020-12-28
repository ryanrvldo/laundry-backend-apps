package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.entity.User;

/**
 * @author Rian Rivaldo
 */
public interface UserService {

  void createUser(User user) throws Exception;

  User getUserByUsername(String username) throws Exception;

  User getUserById(Long id) throws Exception;

  List<User> getAllUser() throws Exception;

  void updateUser(User user) throws Exception;

  void updateUserActiveStatus(User user) throws Exception;

  void updateUserPassword(User user) throws Exception;

  void deleteUserById(Long id) throws Exception;

}
