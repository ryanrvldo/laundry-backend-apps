package com.lawencon.laundry.dao.jdbc.basicImpl;

import com.lawencon.laundry.dao.MemberDao;
import com.lawencon.laundry.entity.Customer;
import com.lawencon.laundry.entity.Member;
import com.lawencon.laundry.entity.Membership;
import com.lawencon.laundry.error.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class MemberDaoImpl extends BaseDaoImpl implements MemberDao {

	@Override
	public Member get(Member data) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_m_member ",
				"WHERE customer_id = ? ");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setLong(1, data.getCustomer().getId());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			Customer customer = new Customer();
			customer.setId(rs.getLong("customer_id"));
			Membership membership = new Membership();
			membership.setId(rs.getLong("membership_id"));
			Member member = new Member();
			member.setId(rs.getLong("id"));
			member.setCustomer(customer);
			member.setMembership(membership);
			member.setExpiredDate(rs.getObject("expired_date", LocalDateTime.class));
			return member;
		}
		return null;
	}

	@Override
	public void insert(Member data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_member (customer_id, membership_id, start_date, expired_date) ",
				"VALUES (?, ?, ?, ?) ",
				"RETURNING id ");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setLong(1, data.getCustomer().getId());
		statement.setLong(2, data.getMembership().getId());
		statement.setObject(3, data.getStartDate());
		statement.setObject(4, data.getExpiredDate());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			data.setId(rs.getLong("id"));
		}
	}

	@Override
	public void update(Member data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(Member data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<Member> getAll() throws Exception {
		throw new NotImplementedException();
	}

}
