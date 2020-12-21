package com.lawencon.laundry.repository.impl;

import com.lawencon.laundry.dao.MembershipDao;
import com.lawencon.laundry.entity.Membership;
import com.lawencon.laundry.repository.MembershipRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class MembershipRepositoryImpl implements MembershipRepository {

	private final MembershipDao dao;

	public MembershipRepositoryImpl(MembershipDao dao) {
		this.dao = dao;
	}

	@Override
	public Membership get(Membership data) throws Exception {
		return dao.get(data);
	}

	@Override
	public void add(Membership data) throws Exception {
		checkBeforeAdd(data, () -> dao.insert(data));
	}

	@Override
	public List<Membership> getAll() throws Exception {
		return Collections.unmodifiableList(dao.getAll());
	}

}
