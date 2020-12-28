package com.lawencon.laundry.dao;

import com.lawencon.laundry.entity.User;

/**
 * @author Rian Rivaldo
 */
public interface UserDao extends BaseDao<User> {

  User findByUsername(String username) throws Exception;

  User findById(Long id) throws Exception;

  void updateActiveStatus(User data) throws Exception;

  void updatePassword(User data) throws Exception;

}
