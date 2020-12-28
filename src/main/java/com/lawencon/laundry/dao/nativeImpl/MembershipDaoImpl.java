package com.lawencon.laundry.dao.nativeImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.MembershipDao;
import com.lawencon.laundry.entity.Membership;

/**
 * @author Rian Rivaldo
 */
@Repository("membership-nq")
public class MembershipDaoImpl extends BaseDaoImpl<Membership> implements MembershipDao {

  public MembershipDaoImpl() {
	super(Membership.class);
  }

  @Override
  public void insert(Membership data) throws Exception {
	String sql = buildQueryOf(
	    "INSERT INTO tb_m_membership (code, membership_name, discount) ",
	    "VALUES (?1, ?2, ?3) RETURNING id");
	Long id = insertWithReturnId(sql, data.getCode(), data.getName(), data.getDiscount());
	data.setId(id);
  }

  @Override
  public void update(Membership data) throws Exception {
	String sql = buildQueryOf(
	    "UPDATE tb_m_membership ",
	    "SET code = ?1, membership_name = ?2, discount = ?3 ",
	    "WHERE id = ?4 ");
	executeUpdateWithQuery(sql, data.getCode(), data.getName(), data.getDiscount(),
	    data.getId());
  }

  @Override
  public void delete(Membership data) throws Exception {
	executeUpdateWithQuery("DELETE FROM tb_m_membership WHERE id = ?1", data.getId());
  }

  @Override
  public List<Membership> findAll() throws Exception {
	String sql = buildQueryOf(
	    "SELECT id, code, membership_name, discount FROM tb_m_membership ");
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

  @Override
  public Membership findById(Long id) throws Exception {
	return getSingleResultWithQuery("SELECT * FROM tb_m_membership WHERE id = ?1", id);
  }

  @Override
  public Membership findByCode(String code) throws Exception {
	return getSingleResultWithQuery("SELECT * FROM tb_m_membership WHERE lower(code) = ?1", code);
  }

}
