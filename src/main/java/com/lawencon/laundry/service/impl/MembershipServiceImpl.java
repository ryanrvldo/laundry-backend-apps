package com.lawencon.laundry.service.impl;

import com.lawencon.laundry.entity.Membership;
import com.lawencon.laundry.repository.MembershipRepository;
import com.lawencon.laundry.service.MembershipService;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class MembershipServiceImpl implements MembershipService {

	private final TransactionTemplate transactionTemplate;
	private final MembershipRepository membershipRepository;

	public MembershipServiceImpl(TransactionTemplate transactionTemplate, MembershipRepository membershipRepository) {
		this.transactionTemplate = transactionTemplate;
		this.membershipRepository = membershipRepository;
	}

	@Override
	public void addAllMemberships(List<Membership> memberships) throws Exception {
		transactionTemplate.executeWithoutResult(val -> {
			for (Membership m : memberships) {
				if (m.getCode() == null || m.getName() == null || m.getDiscount() == null) {
					throw new NullPointerException("Make sure you have been inputted all the required field.");
				}
				try {
					membershipRepository.add(m);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
}
