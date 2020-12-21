package com.lawencon.laundry.dao.jdbc.basicImpl;

import com.lawencon.laundry.dao.CustomerDao;
import com.lawencon.laundry.entity.Customer;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.error.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao {

	@Override
	public Customer get(Customer data) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_m_customer AS c ",
				"WHERE c.profile_id = ? ");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setLong(1, data.getProfile().getId());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			Profile profile = data.getProfile();
			profile.setId(rs.getLong("profile_id"));
			return new Customer(rs.getLong("id"), profile, rs.getObject("created_date", LocalDateTime.class));
		}
		return null;
	}

	@Override
	public void insert(Customer data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_customer (profile_id) ",
				"VALUES (?) ",
				"RETURNING id");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setLong(1, data.getProfile().getId());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			data.setId(rs.getLong("id"));
		}
	}

	@Override
	public void update(Customer data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(Customer data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<Customer> getAll() throws Exception {
		String sql = buildQueryOf(
				"SELECT c.id AS customer_id, p.full_name, p.id AS profile_id, p.phone, p.email, p.address ",
				"FROM tb_m_customer c ",
				"INNER JOIN tb_m_profile p ON p.id = c.profile_id ;");
		Statement statement = getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);

		List<Customer> customerList = new ArrayList<>();
		while (rs.next()) {
			Profile profile = new Profile(
					rs.getLong("profile_id"),
					rs.getString("full_name"),
					rs.getString("phone"),
					rs.getString("email"),
					rs.getString("address"));
			Customer customer = new Customer(rs.getLong("customer_id"), profile, rs.getObject("created_date", LocalDateTime.class));
			customerList.add(customer);
		}
		if (customerList.isEmpty()) {
			return Collections.emptyList();
		}
		return customerList;
	}
}
