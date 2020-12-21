package com.lawencon.laundry.repository.impl;

import com.lawencon.laundry.dao.RoleDao;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.repository.RoleRepository;

/**
 * @author Rian Rivaldo
 */
public class RoleRepositoryImpl implements RoleRepository {

	private final RoleDao dao;

	public RoleRepositoryImpl(RoleDao dao) {
		this.dao = dao;
	}

	@Override
	public Role get(Role data) throws Exception {
		return dao.get(data);
	}

	@Override
	public void add(Role data) throws Exception {
		checkBeforeAdd(data, () -> dao.insert(data));
	}

}
