package com.lawencon.laundry.dao.jdbc.basicImpl;

import com.lawencon.laundry.dao.ProfileDao;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.error.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class ProfileDaoImpl extends BaseDaoImpl implements ProfileDao {

	@Override
	public Profile get(Profile data) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_m_profile ",
				"WHERE phone = ? AND lower(email) = lower(?) ");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setString(1, data.getPhone());
		statement.setString(2, data.getEmail());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Profile profile = new Profile(
					resultSet.getLong("id"),
					resultSet.getString("full_name"),
					resultSet.getString("phone"),
					resultSet.getString("email"),
					resultSet.getString("address"));
			return profile;
		}
		return null;
	}

	@Override
	public void insert(Profile data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_profile (full_name, phone, email, address) ",
				"VALUES (?, ?, ?, ?) ",
				"RETURNING id");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setString(1, data.getFullName());
		statement.setString(2, data.getPhone());
		statement.setString(3, data.getEmail());
		statement.setString(4, data.getAddress());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			data.setId(rs.getLong("id"));
		}
	}

	@Override
	public void update(Profile data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(Profile data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<Profile> getAll() throws Exception {
		throw new NotImplementedException();
	}

}
