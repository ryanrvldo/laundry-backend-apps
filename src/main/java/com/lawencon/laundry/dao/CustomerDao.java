package com.lawencon.laundry.dao;

import com.lawencon.laundry.entity.Customer;
import com.lawencon.laundry.entity.Profile;

/**
 * @author Rian Rivaldo
 */
public interface CustomerDao extends BaseDao<Customer> {

  Customer findById(Long id) throws Exception;

  Customer findByProfileId(Long id) throws Exception;

  default Customer setupCustomer(Object[] objArr) {
	Customer customer = new Customer();
	customer.setId(Long.parseLong(objArr[0].toString()));
	Profile profile = new Profile(
	    Long.parseLong(objArr[1].toString()),
	    (String) objArr[2],
	    (String) objArr[3],
	    (String) objArr[4],
	    (String) objArr[5]);
	customer.setProfile(profile);
	return customer;
  }

}
