package com.lawencon.laundry.repository.impl;

import com.lawencon.laundry.dao.ProfileDao;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.repository.ProfileRepository;

/**
 * @author Rian Rivaldo
 */
public class ProfileRepositoryImpl implements ProfileRepository {

	private final ProfileDao dao;

	public ProfileRepositoryImpl(ProfileDao dao) {
		this.dao = dao;
	}

	@Override
	public Profile get(Profile data) throws Exception {
		return dao.get(data);
	}

	@Override
	public void add(Profile data) throws Exception {
		checkBeforeAdd(data, () -> dao.insert(data));
	}

}
