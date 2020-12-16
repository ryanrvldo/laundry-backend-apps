package com.lawencon.laundry.data.dao;

import com.lawencon.laundry.data.entity.Customer;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface CustomerDao extends BaseDao<Customer> {

	List<Customer> getAllEntity() throws Exception;

}
