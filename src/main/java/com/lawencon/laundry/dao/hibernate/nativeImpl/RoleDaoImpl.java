package com.lawencon.laundry.dao.hibernate.nativeImpl;

import com.lawencon.laundry.dao.RoleDao;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.error.NotImplementedException;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	public RoleDaoImpl() {
		super(Role.class);
	}

	@Override
	public Role get(Role request) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_m_role ",
				"WHERE code = ?1 ");
		return getSingleResultWithQuery(sql, request.getCode());
	}

	@Override
	public void insert(Role request) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void update(Role data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(Role data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<Role> getAll() throws Exception {
		throw new NotImplementedException();
	}

}
