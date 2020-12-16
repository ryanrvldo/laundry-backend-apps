package com.lawencon.laundry.data.dao.impl;

import com.lawencon.laundry.data.dao.RoleDao;
import com.lawencon.laundry.data.entity.Role;
import com.lawencon.laundry.error.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RoleDaoImpl implements RoleDao {

	@Override
	public Role getEntity(Role request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT * FROM tb_m_role ",
				"WHERE code = ? "
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, request.getCode());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Role(
					resultSet.getLong("id"),
					resultSet.getString("code"),
					resultSet.getString("role_name")
			);
		}
		return null;
	}

	@Override
	public Role addEntity(Role request) throws Exception {
		throw new NotImplementedException();
	}

}
