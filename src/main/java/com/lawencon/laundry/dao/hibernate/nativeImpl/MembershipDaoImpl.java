package com.lawencon.laundry.dao.hibernate.nativeImpl;

import com.lawencon.laundry.dao.MembershipDao;
import com.lawencon.laundry.entity.Membership;
import com.lawencon.laundry.error.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class MembershipDaoImpl extends BaseDaoImpl<Membership> implements MembershipDao {

	public MembershipDaoImpl() {
		super(Membership.class);
	}

	@Override
	public Membership get(Membership data) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_m_membership ",
				"WHERE lower(code) = lower(?) ");
		return getSingleResultWithQuery(sql, data.getCode());
	}

	@Override
	public void insert(Membership data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_membership (code, membership_name, discount) ",
				"VALUES (?, ?, ?) ");
		insertWithNoReturnQuery(sql,
				data.getCode(),
				data.getName(),
				data.getDiscount());
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
		List<Membership> membershipList = new ArrayList<>();
		getAllWithQuery(sql, objArr -> {
			Membership membership = new Membership(
					Long.parseLong(objArr[0].toString()),
					(String) objArr[1],
					(String) objArr[2],
					(Double) objArr[3]);
			membershipList.add(membership);
		});
		return membershipList;
	}

}
