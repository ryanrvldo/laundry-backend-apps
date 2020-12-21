package com.lawencon.laundry.dao.jdbc.basicImpl;

import com.lawencon.laundry.dao.RoleDao;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.error.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

	@Override
	public Role get(Role request) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_m_role ",
				"WHERE code = ? ");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setString(1, request.getCode());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			return new Role(
					rs.getLong("id"),
					rs.getString("code"),
					rs.getString("role_name"));
		}
		return null;
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
