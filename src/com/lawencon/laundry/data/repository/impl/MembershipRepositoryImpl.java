package com.lawencon.laundry.data.repository.impl;

import com.lawencon.laundry.data.dao.MembershipDao;
import com.lawencon.laundry.data.dao.impl.MembershipDaoImpl;
import com.lawencon.laundry.data.entity.Membership;
import com.lawencon.laundry.data.repository.MembershipRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class MembershipRepositoryImpl implements MembershipRepository {

	private final MembershipDao dao = new MembershipDaoImpl();

	@Override
	public Membership get(Membership item) throws Exception {
		return dao.getEntity(item);
	}

	@Override
	public Membership add(Membership item) throws Exception {
		return checkBeforeAdd(item, () -> dao.addEntity(item));
	}

	@Override
	public List<Membership> getAll() throws Exception {
		return Collections.unmodifiableList(dao.getAllEntity());
	}

}
