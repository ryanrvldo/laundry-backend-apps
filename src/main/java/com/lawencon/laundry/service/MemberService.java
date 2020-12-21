package com.lawencon.laundry.service;

import com.lawencon.laundry.entity.Customer;
import com.lawencon.laundry.entity.Member;
import com.lawencon.laundry.entity.Membership;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public interface MemberService {

	List<Customer> getCustomerList() throws Exception;

	List<Membership> getMembershipList() throws Exception;

	void addNewCustomer(Customer customer) throws Exception;

	void addNewMember(Member member) throws Exception;

}
