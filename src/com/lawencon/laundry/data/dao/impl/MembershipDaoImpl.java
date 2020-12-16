package com.lawencon.laundry.data.dao.impl;

import com.lawencon.laundry.data.dao.MembershipDao;
import com.lawencon.laundry.data.entity.Membership;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class MembershipDaoImpl implements MembershipDao {

	@Override
	public Membership getEntity(Membership request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT id, code, discount FROM tb_m_membership ",
				"WHERE lower(code) = lower(?) "
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, request.getCode());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Membership membership = new Membership(resultSet.getLong("id"));
			membership.setCode(resultSet.getString("code"));
			membership.setDiscount(resultSet.getInt("discount"));
			return membership;
		}
		return null;
	}

	@Override
	public Membership addEntity(Membership request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_membership (code, package_name, discount) ",
				"VALUES (?, ?, ?) ",
				"RETURNING id "
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, request.getCode());
		statement.setString(2, request.getPackageName());
		statement.setInt(3, request.getDiscount());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Membership(resultSet.getLong("id"));
		}
		return null;
	}

	@Override
	public List<Membership> getAllEntity() throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT id, code, package_name FROM tb_m_membership "
		);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<Membership> membershipList = new ArrayList<>();
		while (resultSet.next()) {
			Membership membership = new Membership(resultSet.getLong("id"));
			membership.setCode(resultSet.getString("code"));
			membership.setPackageName(resultSet.getString("package_name"));
			membershipList.add(membership);
		}
		if (membershipList.isEmpty()) {
			return Collections.emptyList();
		}
		return membershipList;
	}

}
