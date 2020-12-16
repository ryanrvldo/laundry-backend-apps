package com.lawencon.laundry.data.repository.impl;

import com.lawencon.laundry.data.dao.CustomerDao;
import com.lawencon.laundry.data.dao.impl.CustomerDaoImpl;
import com.lawencon.laundry.data.entity.Customer;
import com.lawencon.laundry.data.repository.CustomerRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CustomerRepositoryImpl implements CustomerRepository {

	private final CustomerDao dao = new CustomerDaoImpl();

	@Override
	public Customer get(Customer item) throws Exception {
		return dao.getEntity(item);
	}

	@Override
	public Customer add(Customer item) throws Exception {
		return checkBeforeAdd(item, () -> dao.addEntity(item));
	}

	@Override
	public List<Customer> getAll() throws Exception {
		return Collections.unmodifiableList(dao.getAllEntity());
	}

}
