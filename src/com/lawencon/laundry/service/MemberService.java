package com.lawencon.laundry.service;

import com.lawencon.laundry.data.entity.Customer;
import com.lawencon.laundry.data.entity.Member;
import com.lawencon.laundry.data.entity.Membership;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface MemberService {

	List<Customer> getCustomerList() throws Exception;

	List<Membership> getMembershipList() throws Exception;

	Customer addNewCustomer(Customer customer) throws Exception;

	void addNewMember(Member member) throws Exception;

}
