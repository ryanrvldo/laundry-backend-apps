package com.lawencon.laundry.dao;

import com.lawencon.laundry.entity.Membership;

/**
 * @author Rian Rivaldo
 */
public interface MembershipDao extends BaseDao<Membership> {

  Membership findById(Long id) throws Exception;

  Membership findByCode(String code) throws Exception;

}
