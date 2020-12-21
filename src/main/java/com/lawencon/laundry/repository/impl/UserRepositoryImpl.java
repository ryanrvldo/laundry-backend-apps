package com.lawencon.laundry.repository.impl;

import com.lawencon.laundry.dao.UserDao;
import com.lawencon.laundry.entity.User;
import com.lawencon.laundry.repository.UserRepository;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class UserRepositoryImpl implements UserRepository {

	private final UserDao dao;

	public UserRepositoryImpl(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public User get(User data) throws Exception {
		return dao.get(data);
	}

	@Override
	public void add(User data) throws Exception {
		checkBeforeAdd(data, () -> dao.insert(data));
	}

	@Override
	public List<User> getAll() throws Exception {
		return dao.getAll();
	}

}
