package com.lawencon.laundry.data.repository;

import com.lawencon.laundry.data.entity.Membership;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface MembershipRepository extends BaseRepository<Membership> {

	List<Membership> getAll() throws Exception;

}
