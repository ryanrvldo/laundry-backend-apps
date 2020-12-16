package com.lawencon.laundry.service;

import com.lawencon.laundry.data.entity.Membership;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface MembershipService {

	void addAllMemberships(List<Membership> memberships) throws Exception;

}
