package com.lawencon.laundry.data.repository.impl;

import com.lawencon.laundry.data.dao.ProfileDao;
import com.lawencon.laundry.data.dao.impl.ProfileDaoImpl;
import com.lawencon.laundry.data.entity.Profile;
import com.lawencon.laundry.data.repository.ProfileRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ProfileRepositoryImpl implements ProfileRepository {

	private final ProfileDao dao = new ProfileDaoImpl();

	@Override
	public Profile get(Profile item) throws Exception {
		return dao.getEntity(item);
	}

	@Override
	public Profile add(Profile item) throws Exception {
		return checkBeforeAdd(item, () -> dao.addEntity(item));
	}

}
