package com.lawencon.laundry.dao.nativeImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.CustomerDao;
import com.lawencon.laundry.entity.Customer;

/**
 * @author Rian Rivaldo
 */
@Repository("customer-nq")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

  public CustomerDaoImpl() {
	super(Customer.class);
  }

  private final String getCustomerNativeQuery = buildQueryOf(
      "SELECT c.id, c.profile_id, p.full_name, p.phone, p.email, p.address ",
      "FROM tb_m_customer c ",
      "INNER JOIN tb_m_profile p ON p.id = c.profile_id ");

  @Override
  public void insert(Customer data) throws Exception {
	String sql = buildQueryOf(
	    "INSERT INTO tb_m_customer (profile_id, created_date) ",
	    "VALUES (?1, ?2) RETURNING id ");
	Long id = insertWithReturnId(sql, BigInteger.valueOf(data.getProfile().getId()), data.getCreatedDate());
	data.setId(id);
  }

  @Override
  public void update(Customer data) throws Exception {
	executeUpdateWithQuery("UPDATE tb_m_customer SET profile_id = ?1 WHERE id = ?2",
	    BigInteger.valueOf(data.getProfile().getId()),
	    BigInteger.valueOf(data.getId()));
  }

  @Override
  public void delete(Customer data) throws Exception {
	executeUpdateWithQuery("DELETE FROM tb_m_customer WHERE id = ?1", data.getId());
  }

  @Override
  public List<Customer> findAll() throws Exception {
	List<Customer> customerList = new ArrayList<>();
	getAllWithQuery(getCustomerNativeQuery, objArr -> {
	  customerList.add(setupCustomer(objArr));
	});
	return customerList;
  }

  @Override
  public Customer findById(Long id) throws Exception {
	String sql = getCustomerNativeQuery + "WHERE c.id = ?1";
	Object obj = getEntityManager().createNativeQuery(sql)
	    .setParameter(1, id)
	    .getSingleResult();
	return setupCustomer((Object[]) obj);
  }

  @Override
  public Customer findByProfileId(Long id) throws Exception {
	String sql = getCustomerNativeQuery + "WHERE p.id = ?1";
	Object obj = getEntityManager().createNativeQuery(sql)
	    .setParameter(1, id)
	    .getSingleResult();
	return setupCustomer((Object[]) obj);
  }

}
