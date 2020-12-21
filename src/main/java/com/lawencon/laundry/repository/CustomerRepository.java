package com.lawencon.laundry.repository;

import com.lawencon.laundry.entity.Customer;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public interface CustomerRepository extends BaseRepository<Customer> {

	List<Customer> getAll() throws Exception;

}
