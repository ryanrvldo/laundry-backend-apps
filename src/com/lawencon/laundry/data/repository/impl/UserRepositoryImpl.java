package com.lawencon.laundry.data.repository.impl;

import com.lawencon.laundry.data.dao.UserDao;
import com.lawencon.laundry.data.dao.impl.UserDaoImpl;
import com.lawencon.laundry.data.entity.User;
import com.lawencon.laundry.data.repository.UserRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserRepositoryImpl implements UserRepository {

	private final UserDao dao = new UserDaoImpl();

	@Override
	public User get(User item) throws Exception {
		return dao.getEntity(item);
	}

	@Override
	public User add(User item) throws Exception {
		return checkBeforeAdd(item, () -> dao.addEntity(item));
	}

}
