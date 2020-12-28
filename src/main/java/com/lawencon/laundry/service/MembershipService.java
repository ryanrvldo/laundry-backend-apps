package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.entity.Membership;

/**
 * @author Rian Rivaldo
 */
public interface MembershipService {

  void createMembership(Membership membership) throws Exception;

  void updateMembership(Membership membership) throws Exception;

  void deleteMembershipById(Long id) throws Exception;

  List<Membership> getAll() throws Exception;

  Membership getMembershipById(Long id) throws Exception;

  Membership getMembershipByCode(String code) throws Exception;

}
