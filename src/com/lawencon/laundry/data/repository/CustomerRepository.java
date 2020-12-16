package com.lawencon.laundry.data.repository;

import com.lawencon.laundry.data.entity.Customer;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface CustomerRepository extends BaseRepository<Customer> {

	List<Customer> getAll() throws Exception;

}
