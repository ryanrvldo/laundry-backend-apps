package com.lawencon.laundry.dao.hibernate.nativeImpl;

import com.lawencon.laundry.dao.MemberDao;
import com.lawencon.laundry.entity.Member;
import com.lawencon.laundry.error.NotImplementedException;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {

	public MemberDaoImpl() {
		super(Member.class);
	}

	@Override
	public Member get(Member data) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_m_member ",
				"WHERE customer_id = ? ");
		return getSingleResultWithQuery(sql, data.getCustomer().getId());
	}

	@Override
	public void insert(Member data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_member (customer_id, membership_id, start_date, expired_date) ",
				"VALUES (?, ?, ?, ?) ",
				"RETURNING id ");
		insertWithNoReturnQuery(sql,
				data.getCustomer().getId(),
				data.getMembership().getId(),
				data.getStartDate(),
				data.getExpiredDate());
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
