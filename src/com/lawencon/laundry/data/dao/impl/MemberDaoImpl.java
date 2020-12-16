package com.lawencon.laundry.data.dao.impl;

import com.lawencon.laundry.data.dao.MemberDao;
import com.lawencon.laundry.data.entity.Customer;
import com.lawencon.laundry.data.entity.Member;
import com.lawencon.laundry.data.entity.Membership;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * @author Rian Rivaldo Rumapea
 */
public class MemberDaoImpl implements MemberDao {

	@Override
	public Member getEntity(Member request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT * FROM tb_m_member ",
				"WHERE customer_id = ? "
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, request.getCustomer().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Customer customer = new Customer();
			customer.setId(resultSet.getLong("customer_id"));
			Membership membership = new Membership(resultSet.getLong("membership_id"));

			Member member = new Member(resultSet.getLong("id"));
			member.setCustomer(customer);
			member.setMembership(membership);
			member.setExpiredAt(resultSet.getObject("expired_at", LocalDate.class));
			return member;
		}
		return null;
	}

	@Override
	public Member addEntity(Member request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_member (customer_id, membership_id, expired_at) ",
				"VALUES (?, ?, ?) ",
				"RETURNING id "
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, request.getCustomer().getId());
		statement.setLong(2, request.getMembership().getId());
		statement.setObject(3, request.getExpiredAt());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Member(resultSet.getLong("id"));
		}
		return null;
	}

}
