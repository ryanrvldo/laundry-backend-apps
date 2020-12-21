package com.lawencon.laundry.dao.jdbc.basicImpl;

import com.lawencon.laundry.dao.MembershipDao;
import com.lawencon.laundry.entity.Membership;
import com.lawencon.laundry.error.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class MembershipDaoImpl extends BaseDaoImpl implements MembershipDao {

	@Override
	public Membership get(Membership data) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_m_membership ",
				"WHERE lower(code) = lower(?) ");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setString(1, data.getCode());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			Membership membership = new Membership(
					rs.getLong("id"),
					rs.getString("code"),
					rs.getString("membership_name"),
					rs.getDouble("discount"));
			return membership;
		}
		return null;
	}

	@Override
	public void insert(Membership data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_membership (code, membership_name, discount) ",
				"VALUES (?, ?, ?) ",
				"RETURNING id ");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setString(1, data.getCode());
		statement.setString(2, data.getName());
		statement.setDouble(3, data.getDiscount());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			data.setId(rs.getLong("id"));
		}
	}

	@Override
	public void update(Membership data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(Membership data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<Membership> getAll() throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_m_membership ");
		Statement statement = getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);

		List<Membership> membershipList = new ArrayList<>();
		while (rs.next()) {
			Membership membership = new Membership(
					rs.getLong("id"),
					rs.getString("code"),
					rs.getString("membership_name"),
					rs.getDouble("discount"));
			membershipList.add(membership);
		}
		if (membershipList.isEmpty()) {
			return Collections.emptyList();
		}
		return membershipList;
	}

}
