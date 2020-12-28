package com.lawencon.laundry.dao.nativeImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.MemberDao;
import com.lawencon.laundry.entity.Member;
import com.lawencon.laundry.error.NotImplementedException;

/**
 * @author Rian Rivaldo
 */
@Repository("member-nq")
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {

  public MemberDaoImpl() {
	super(Member.class);
  }

  @Override
  public void insert(Member data) throws Exception {
	String sql = buildQueryOf(
	    "INSERT INTO tb_m_member (customer_id, membership_id, start_date, expired_date) ",
	    "VALUES (?, ?, ?, ?) ",
	    "RETURNING id ");
	executeUpdateWithQuery(sql,
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
  public List<Member> findAll() throws Exception {
	throw new NotImplementedException();
  }

}
