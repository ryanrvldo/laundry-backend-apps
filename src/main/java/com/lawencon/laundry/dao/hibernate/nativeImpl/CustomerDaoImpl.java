package com.lawencon.laundry.dao.hibernate.nativeImpl;

import com.lawencon.laundry.dao.CustomerDao;
import com.lawencon.laundry.entity.Customer;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.error.NotImplementedException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	public CustomerDaoImpl() {
		super(Customer.class);
	}

	@Override
	public Customer get(Customer data) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_m_customer AS c ",
				"WHERE c.profile_id = ?1 ");
		return getSingleResultWithQuery(sql, BigInteger.valueOf(data.getProfile().getId()));
	}

	@Override
	public void insert(Customer data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_customer (profile_id, created_date) ",
				"VALUES (?1, current_timestamp) ",
				"RETURNING id ");
		Long id = insertWithReturnId(sql, BigInteger.valueOf(data.getProfile().getId()));
		data.setId(id);
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
				"SELECT c.id AS customer_id, p.id AS profile_id, p.full_name, p.phone, p.email, p.address ",
				"FROM tb_m_customer c ",
				"INNER JOIN tb_m_profile p ON p.id = c.profile_id ");
		List<Customer> customerList = new ArrayList<>();
		getAllWithQuery(sql, objArr -> {
			Customer customer = new Customer();
			customer.setId(Long.parseLong(objArr[0].toString()));
			Profile profile = new Profile(
					Long.parseLong(objArr[1].toString()),
					(String) objArr[2],
					(String) objArr[3],
					(String) objArr[4],
					(String) objArr[5]
			);
			customer.setProfile(profile);
			customerList.add(customer);
		});
		return customerList;
	}

}
