package com.lawencon.laundry.service.impl;

import com.lawencon.laundry.data.entity.Customer;
import com.lawencon.laundry.data.entity.Member;
import com.lawencon.laundry.data.entity.Membership;
import com.lawencon.laundry.data.entity.Profile;
import com.lawencon.laundry.data.repository.CustomerRepository;
import com.lawencon.laundry.data.repository.MemberRepository;
import com.lawencon.laundry.data.repository.MembershipRepository;
import com.lawencon.laundry.data.repository.ProfileRepository;
import com.lawencon.laundry.data.repository.impl.CustomerRepositoryImpl;
import com.lawencon.laundry.data.repository.impl.MemberRepositoryImpl;
import com.lawencon.laundry.data.repository.impl.MembershipRepositoryImpl;
import com.lawencon.laundry.data.repository.impl.ProfileRepositoryImpl;
import com.lawencon.laundry.service.MemberService;

import java.util.List;
import java.util.Optional;

/**
 * @author Rian Rivaldo Rumapea
 */
public class MemberServiceImpl implements MemberService {

	private final ProfileRepository profileRepository = new ProfileRepositoryImpl();
	private final CustomerRepository customerRepository = new CustomerRepositoryImpl();
	private final MemberRepository memberRepository = new MemberRepositoryImpl();
	private final MembershipRepository membershipRepository = new MembershipRepositoryImpl();

	@Override
	public List<Customer> getCustomerList() throws Exception {
		List<Customer> customerList = customerRepository.getAll();
		if (customerList.isEmpty()) {
			throw new NullPointerException("There is no customer yet. Please add first.");
		}
		return customerList;
	}

	@Override
	public List<Membership> getMembershipList() throws Exception {
		List<Membership> membershipList = membershipRepository.getAll();
		if (membershipList.isEmpty()) {
			throw new NullPointerException("There is no membership yet. Please add first.");
		}
		return membershipList;
	}

	@Override
	public Customer addNewCustomer(Customer customer) throws Exception {
		Profile addedProfile = Optional.ofNullable(profileRepository.add(customer.getProfile()))
				.orElseThrow(() -> new Exception("Failed to add customer profile entity to database."));
		customer.setProfile(addedProfile);
		return Optional.ofNullable(customerRepository.add(customer))
				.orElseThrow(() -> new Exception("Failed to add new customer."));
	}

	@Override
	public void addNewMember(Member member) throws Exception {
		if (member.getCustomer() == null || member.getCustomer().getId() == null ||
				member.getMembership() == null || member.getMembership().getId() == null || member.getExpiredAt() == null) {
			throw new NullPointerException("Make sure you have been inputted all the required field.");
		}

		Optional.ofNullable(memberRepository.add(member))
				.orElseThrow(() -> new Exception("Failed to add new member"));
	}

}
