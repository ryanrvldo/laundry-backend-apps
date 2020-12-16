package com.lawencon.laundry.data.dao.impl;

import com.lawencon.laundry.data.dao.ProfileDao;
import com.lawencon.laundry.data.entity.Profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ProfileDaoImpl implements ProfileDao {
	@Override
	public Profile getEntity(Profile request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT * FROM tb_m_profile ",
				"WHERE phone = ? AND lower(email) = lower(?) "
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, request.getPhone());
		statement.setString(2, request.getEmail());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Profile profile = new Profile(
					resultSet.getString("full_name"),
					resultSet.getString("phone"),
					resultSet.getString("email"),
					resultSet.getString("address")
			);
			profile.setId(resultSet.getLong("id"));
			return profile;
		}
		return null;
	}

	@Override
	public Profile addEntity(Profile request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_profile (full_name, phone, email, address) ",
				"VALUES (?, ?, ?, ?) ",
				"RETURNING id"
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, request.getFullName());
		statement.setString(2, request.getPhone());
		statement.setString(3, request.getEmail());
		statement.setString(4, request.getAddress());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Profile(resultSet.getLong("id"));
		}
		return null;
	}
}
