package com.lawencon.laundry.dao.jdbc.basicImpl;

import com.lawencon.laundry.dao.UserDao;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.entity.User;
import com.lawencon.laundry.error.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public User get(User data) throws Exception {
		String sql = buildQueryOf(
				"SELECT u.id AS user_id, u.username, u.profile_id, u.is_active, u.role_id, r.code, r.role_name ",
				"FROM tb_m_user AS u ",
				"INNER JOIN tb_m_role AS r ON r.id = u.role_id ",
				"WHERE lower(u.username) = lower(?) AND u.user_password = lower(?) ");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setString(1, data.getUsername());
		statement.setString(2, data.getPassword());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			Profile profile = new Profile();
			profile.setId(rs.getLong("profile_id"));
			Role role = new Role(
					rs.getLong("role_id"),
					rs.getString("code"),
					rs.getString("role_name"));
			return new User(
					rs.getLong("user_id"),
					rs.getString("username"),
					null,
					profile,
					role,
					rs.getBoolean("is_active"));
		}
		return null;
	}

	@Override
	public void insert(User data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_user (username, user_password, profile_id, role_id) ",
				"VALUES (?, ?, ?, ?) ",
				"RETURNING id ");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setString(1, data.getUsername());
		statement.setString(2, data.getPassword());
		statement.setLong(3, data.getProfile().getId());
		statement.setLong(4, data.getRole().getId());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			data.setId(rs.getLong("id"));
		}
	}

	@Override
	public void update(User data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(User data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<User> getAll() throws Exception {
		throw new NotImplementedException();
	}

}
