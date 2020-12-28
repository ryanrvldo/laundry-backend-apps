package com.lawencon.laundry.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.MembershipDao;
import com.lawencon.laundry.entity.Membership;
import com.lawencon.laundry.error.DataIsNotExistsException;
import com.lawencon.laundry.service.MembershipService;
import com.lawencon.laundry.util.ValidationUtils;

/**
 * @author Rian Rivaldo
 */
@Service
public class MembershipServiceImpl implements MembershipService {

  @Autowired
  private ValidationUtils validationUtils;

  @Autowired
  @Qualifier("membership-jpa")
  private MembershipDao membershipDao;

  @Override
  public void createMembership(Membership membership) throws Exception {
	Objects.requireNonNull(membership, () -> "Membership data must be submitted.");
	validationUtils.validateString(membership.getCode(), membership.getName());
	if (membership.getDiscount() <= 0) {
	  throw new IllegalArgumentException("Membership discount must be greater than 0.");
	}
	membershipDao.insert(membership);
  }

  @Transactional
  @Override
  public void updateMembership(Membership membership) throws Exception {
	Long id = Optional.ofNullable(membership)
	    .map(i -> i.getId())
	    .orElseThrow(() -> new NullPointerException("Membership id must be submitted"));
	validationUtils.validateEntityId(id, () -> getMembershipById(id));
	validationUtils.validateString(membership.getCode(), membership.getName());
	if (membership.getDiscount() <= 0) {
	  throw new IllegalArgumentException("Membership discount must be greater than 0.");
	}
	membershipDao.update(membership);
  }

  @Transactional
  @Override
  public void deleteMembershipById(Long id) throws Exception {
	Objects.requireNonNull(id, () -> "Membership id must be submitted.");
	Membership membership = validationUtils.validateEntityId(id, () -> getMembershipById(id));
	membershipDao.delete(membership);
  }

  @Override
  public List<Membership> getAll() throws Exception {
	List<Membership> membershipList = membershipDao.findAll();
	if (membershipList.isEmpty()) {
	  throw new NullPointerException("Membership data is empty.");
	}
	return membershipList;
  }

  @Override
  public Membership getMembershipById(Long id) throws Exception {
	Objects.requireNonNull(id, () -> "Membership id must be submitted.");
	validationUtils.validateEntityId(id, null);
	return Optional.ofNullable(validationUtils.validateGet(() -> membershipDao.findById(id)))
	    .orElseThrow(() -> new DataIsNotExistsException(id));
  }

  @Override
  public Membership getMembershipByCode(String code) throws Exception {
	Objects.requireNonNull(code, () -> "Membership code must be submitted.");
	return Optional.ofNullable(validationUtils.validateGet(() -> membershipDao.findByCode(code)))
	    .orElseThrow(() -> new DataIsNotExistsException(code));
  }

}
