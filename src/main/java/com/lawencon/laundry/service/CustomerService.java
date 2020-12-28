package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.entity.Customer;

/**
 * @author Rian Rivaldo
 */

public interface CustomerService {

  void createCustomer(Customer customer) throws Exception;

  Customer getCustomerById(Long id) throws Exception;

  Customer getCustomerByProfileId(Long id) throws Exception;

  List<Customer> getAllCustomer() throws Exception;

  void updateCustomer(Customer customer) throws Exception;

  void deleteCustomerById(Long id) throws Exception;

}
