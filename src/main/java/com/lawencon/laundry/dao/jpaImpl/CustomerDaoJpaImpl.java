package com.lawencon.laundry.dao.jpaImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.CustomerDao;
import com.lawencon.laundry.entity.Customer;
import com.lawencon.laundry.repository.CustomerRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("customer-jpa")
public class CustomerDaoJpaImpl implements CustomerDao {

  @Autowired
  private CustomerRepository repository;

  @Override
  public void insert(Customer data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Customer data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Customer data) throws Exception {
	repository.deleteById(data.getId());
  }

  @Override
  public List<Customer> findAll() throws Exception {
	List<Customer> customerList = new ArrayList<>();
	repository.findAllWithProfile()
	    .forEach(objArr -> customerList.add(setupCustomer(objArr)));
	return customerList;
  }

  @Override
  public Customer findById(Long id) throws Exception {
	Object obj = repository.findCustomerById(id);
	return setupCustomer((Object[]) obj);
  }

  @Override
  public Customer findByProfileId(Long id) throws Exception {
	Object obj = repository.findByProfileId(id);
	return setupCustomer((Object[]) obj);
  }

}
