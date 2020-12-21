package com.lawencon.laundry.repository;

import com.lawencon.laundry.entity.Membership;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public interface MembershipRepository extends BaseRepository<Membership> {

	List<Membership> getAll() throws Exception;

}
