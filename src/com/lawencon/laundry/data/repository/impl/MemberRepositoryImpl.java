package com.lawencon.laundry.data.repository.impl;

import com.lawencon.laundry.data.dao.MemberDao;
import com.lawencon.laundry.data.dao.impl.MemberDaoImpl;
import com.lawencon.laundry.data.entity.Member;
import com.lawencon.laundry.data.repository.MemberRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class MemberRepositoryImpl implements MemberRepository {

	private final MemberDao dao = new MemberDaoImpl();

	@Override
	public Member get(Member item) throws Exception {
		return dao.getEntity(item);
	}

	@Override
	public Member add(Member item) throws Exception {
		return checkBeforeAdd(item, () -> dao.addEntity(item));
	}

}
