package com.lawencon.laundry.data.dao.impl;

import com.lawencon.laundry.data.dao.CustomerDao;
import com.lawencon.laundry.data.entity.Customer;
import com.lawencon.laundry.data.entity.Profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Customer getEntity(Customer request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT * FROM tb_m_customer AS c ",
				"WHERE c.profile_id = ? "
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, request.getProfile().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Profile profile = new Profile(resultSet.getLong("profile_id"));
			return new Customer(resultSet.getLong("id"), profile);
		}
		return null;
	}

	@Override
	public Customer addEntity(Customer request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_customer (profile_id) ",
				"VALUES (?) ",
				"RETURNING id"
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, request.getProfile().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Customer(resultSet.getLong("id"), null);
		}
		return null;
	}

	@Override
	public List<Customer> getAllEntity() throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT c.id, p.full_name, p.phone, p.email, p.address ",
				"FROM tb_m_customer c ",
				"INNER JOIN tb_m_profile p ON p.id = c.profile_id ;"
		);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<Customer> customerList = new ArrayList<>();
		while (resultSet.next()) {
			Profile profile = new Profile(
					resultSet.getString("full_name"),
					resultSet.getString("phone"),
					resultSet.getString("email"),
					resultSet.getString("address"));
			Customer customer = new Customer(resultSet.getLong("id"), profile);
			customerList.add(customer);
		}
		if (customerList.isEmpty()) {
			return Collections.emptyList();
		}
		return customerList;
	}
}
