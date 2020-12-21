package com.lawencon.laundry.service.impl;

import com.lawencon.laundry.entity.Customer;
import com.lawencon.laundry.entity.Member;
import com.lawencon.laundry.entity.Membership;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.repository.CustomerRepository;
import com.lawencon.laundry.repository.MemberRepository;
import com.lawencon.laundry.repository.MembershipRepository;
import com.lawencon.laundry.repository.ProfileRepository;
import com.lawencon.laundry.service.MemberService;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Rian Rivaldo
 */
public class MemberServiceImpl implements MemberService {

	private final TransactionTemplate transactionTemplate;
	private final ProfileRepository profileRepository;
	private final CustomerRepository customerRepository;
	private final MemberRepository memberRepository;
	private final MembershipRepository membershipRepository;

	public MemberServiceImpl(TransactionTemplate transactionTemplate, ProfileRepository profileRepository, CustomerRepository customerRepository, MemberRepository memberRepository,
	                         MembershipRepository membershipRepository) {
		this.transactionTemplate = transactionTemplate;
		this.profileRepository = profileRepository;
		this.customerRepository = customerRepository;
		this.memberRepository = memberRepository;
		this.membershipRepository = membershipRepository;
	}

	@Override
	public List<Customer> getCustomerList() throws Exception {
		List<Customer> customerList = transactionTemplate.execute(val -> {
			try {
				return customerRepository.getAll();
			} catch (Exception e) {
				return Collections.emptyList();
			}
		});

		if (customerList != null && customerList.isEmpty()) {
			throw new Exception("There is no customer yet. Please add first.");
		}
		return customerList;
	}

	@Override
	public List<Membership> getMembershipList() throws Exception {
		List<Membership> membershipList = transactionTemplate.execute(val -> {
			try {
				return membershipRepository.getAll();
			} catch (Exception e) {
				return Collections.emptyList();
			}
		});

		if (membershipList != null && membershipList.isEmpty()) {
			throw new Exception("There is no membership yet. Please add first.");
		}
		return membershipList;
	}

	@Override
	public void addNewCustomer(Customer customer) throws Exception {
		Optional.ofNullable(customer)
				.orElseThrow(() -> new Exception("Customer is empty!"));

		transactionTemplate.executeWithoutResult(val -> {
			Profile profile = customer.getProfile();
			try {
				profileRepository.add(profile);
				customer.setProfile(profile);
				customerRepository.add(customer);
				Optional.ofNullable(customer.getId())
						.orElseThrow(() -> new Exception("Failed to add new customer."));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public void addNewMember(Member member) throws Exception {
		if (member.getCustomer() == null || member.getCustomer().getId() == null ||
				member.getMembership() == null || member.getMembership().getId() == null || member.getExpiredDate() == null) {
			throw new Exception("Make sure you have been inputted all the required field.");
		}

		transactionTemplate.executeWithoutResult(val -> {
			try {
				memberRepository.add(member);
				Optional.ofNullable(member.getId())
						.orElseThrow(() -> new Exception("Failed to add new member"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

}
