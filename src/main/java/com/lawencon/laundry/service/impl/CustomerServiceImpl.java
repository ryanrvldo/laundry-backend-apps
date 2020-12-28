package com.lawencon.laundry.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.CustomerDao;
import com.lawencon.laundry.entity.Customer;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.service.CustomerService;
import com.lawencon.laundry.service.ProfileService;
import com.lawencon.laundry.util.ValidationUtils;

/**
 * @author Rian Rivaldo
 */
@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private ValidationUtils validationUtils;

  @Autowired
  @Qualifier("customer-nq")
  private CustomerDao customerDao;

  @Autowired
  private ProfileService profileService;

  @Transactional
  @Override
  public void createCustomer(Customer customer) throws Exception {
	validateCustomer(customer);
	customer.setCreatedDate(LocalDateTime.now());
	profileService.createProfile(customer.getProfile());
	customerDao.insert(customer);
  }

  @Override
  public Customer getCustomerById(Long id) throws Exception {
	return validationUtils.validateEntityId(id, () -> customerDao.findById(id));
  }

  @Override
  public Customer getCustomerByProfileId(Long id) throws Exception {
	return validationUtils.validateEntityId(id, () -> customerDao.findByProfileId(id));
  }

  @Override
  public List<Customer> getAllCustomer() throws Exception {
	List<Customer> customerList = customerDao.findAll();
	if (customerList.isEmpty()) {
	  throw new NullPointerException("Customer data is empty.");
	}
	return customerList;
  }

  @Transactional
  @Override
  public void updateCustomer(Customer customer) throws Exception {
	validateCustomer(customer);
	Profile profile = profileService.getProfileByEmail(customer.getProfile().getEmail());
	customer.getProfile().setId(profile.getId());
	profileService.updateProfile(customer.getProfile());
	customerDao.update(customer);
  }

  @Transactional
  @Override
  public void deleteCustomerById(Long id) throws Exception {
	Customer customer = validationUtils.validateEntityId(id, () -> customerDao.findById(id));
	customerDao.delete(customer);
	profileService.deleteProfileById(customer.getProfile().getId());
  }

  private void validateCustomer(Customer customer) throws Exception {
	Objects.requireNonNull(customer, () -> "Customer data must be submitted.");
	Objects.requireNonNull(customer.getProfile(), () -> "Customer profile must be submitted.");
  }

}
