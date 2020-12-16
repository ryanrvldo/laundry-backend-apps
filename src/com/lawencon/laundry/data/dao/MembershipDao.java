package com.lawencon.laundry.data.dao;

import com.lawencon.laundry.data.entity.Membership;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface MembershipDao extends BaseDao<Membership> {

	List<Membership> getAllEntity() throws Exception;

}
