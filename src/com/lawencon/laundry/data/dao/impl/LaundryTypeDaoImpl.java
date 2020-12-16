package com.lawencon.laundry.data.dao.impl;

import com.lawencon.laundry.data.dao.LaundryTypeDao;
import com.lawencon.laundry.data.entity.LaundryType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LaundryTypeDaoImpl implements LaundryTypeDao {

	@Override
	public LaundryType getEntity(LaundryType request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT id, code FROM tb_m_laundry_type ",
				"WHERE lower(code) = lower(?) "
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, request.getCode());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			LaundryType laundryType = new LaundryType(resultSet.getLong("id"));
			laundryType.setCode(resultSet.getString("code"));
			return laundryType;
		}
		return null;
	}

	@Override
	public LaundryType addEntity(LaundryType request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_laundry_type (code, type_name) ",
				"VALUES (?, ?) ",
				"RETURNING id"
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, request.getCode());
		statement.setString(2, request.getName());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new LaundryType(resultSet.getLong("id"));
		}
		return null;
	}

}
