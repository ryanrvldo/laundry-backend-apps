package com.lawencon.laundry.data.dao.impl;

import com.lawencon.laundry.data.dao.UserDao;
import com.lawencon.laundry.data.entity.Profile;
import com.lawencon.laundry.data.entity.Role;
import com.lawencon.laundry.data.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserDaoImpl implements UserDao {

	@Override
	public User getEntity(User request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT u.id AS user_id, u.username, u.is_active, u.profile_id, u.role_id, r.code, r.role_name ",
				"FROM tb_m_user AS u ",
				"INNER JOIN tb_m_role AS r ON r.id = u.role_id ",
				"WHERE lower(u.username) = lower(?) AND u.user_password = crypt(?, u.user_password) "
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, request.getUsername());
		statement.setString(2, request.getPassword());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Profile profile = new Profile(resultSet.getLong("profile_id"));
			Role role = new Role(
					resultSet.getLong("role_id"),
					resultSet.getString("code"),
					resultSet.getString("role_name")
			);
			return new User(
					resultSet.getLong("user_id"),
					resultSet.getString("username"),
					resultSet.getBoolean("is_active"),
					profile,
					role
			);
		}
		return null;
	}

	@Override
	public User addEntity(User request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_user (username, user_password, profile_id, role_id) ",
				"VALUES (?, crypt(?, gen_salt('bf')), ?, ?) ",
				"RETURNING id "
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, request.getUsername());
		statement.setString(2, request.getPassword());
		statement.setLong(3, request.getProfile().getId());
		statement.setLong(4, request.getRole().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new User(resultSet.getLong("id"));
		}
		return null;
	}

}
