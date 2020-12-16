package com.lawencon.laundry.service.impl;

import com.lawencon.laundry.data.entity.Membership;
import com.lawencon.laundry.data.repository.MembershipRepository;
import com.lawencon.laundry.data.repository.impl.MembershipRepositoryImpl;
import com.lawencon.laundry.service.MembershipService;

import java.util.List;
import java.util.Optional;

/**
 * @author Rian Rivaldo Rumapea
 */
public class MembershipServiceImpl implements MembershipService {

	private final MembershipRepository membershipRepository = new MembershipRepositoryImpl();

	@Override
	public void addAllMemberships(List<Membership> memberships) throws Exception {
		for (Membership m : memberships) {
			if (m.getCode() == null || m.getPackageName() == null || m.getDiscount() == null) {
				throw new NullPointerException("Make sure you have been inputted all the required field.");
			}

			Optional.ofNullable(membershipRepository.add(m))
					.orElseThrow(() -> new Exception("Failed to add new membership"));
		}
	}
}
