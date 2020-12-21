package com.lawencon.laundry.repository.impl;

import com.lawencon.laundry.dao.CustomerDao;
import com.lawencon.laundry.entity.Customer;
import com.lawencon.laundry.repository.CustomerRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class CustomerRepositoryImpl implements CustomerRepository {

	private final CustomerDao dao;

	public CustomerRepositoryImpl(CustomerDao dao) {
		this.dao = dao;
	}

	@Override
	public Customer get(Customer data) throws Exception {
		return dao.get(data);
	}

	@Override
	public void add(Customer data) throws Exception {
		checkBeforeAdd(data, () -> dao.insert(data));
	}

	@Override
	public List<Customer> getAll() throws Exception {
		return Collections.unmodifiableList(dao.getAll());
	}

}
