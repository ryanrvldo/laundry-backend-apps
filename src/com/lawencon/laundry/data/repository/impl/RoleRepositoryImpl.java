package com.lawencon.laundry.data.repository.impl;

import com.lawencon.laundry.data.dao.RoleDao;
import com.lawencon.laundry.data.dao.impl.RoleDaoImpl;
import com.lawencon.laundry.data.entity.Role;
import com.lawencon.laundry.data.repository.RoleRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RoleRepositoryImpl implements RoleRepository {

	private final RoleDao dao = new RoleDaoImpl();

	@Override
	public Role get(Role item) throws Exception {
		return dao.getEntity(item);
	}

	@Override
	public Role add(Role item) throws Exception {
		return dao.addEntity(item);
	}

}
