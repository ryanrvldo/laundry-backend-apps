package com.lawencon.laundry.service;

import com.lawencon.laundry.entity.Membership;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public interface MembershipService {

	void addAllMemberships(List<Membership> memberships) throws Exception;

}
