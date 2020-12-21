package com.lawencon.laundry.repository.impl;

import com.lawencon.laundry.dao.MemberDao;
import com.lawencon.laundry.dao.jdbc.basicImpl.MemberDaoImpl;
import com.lawencon.laundry.entity.Member;
import com.lawencon.laundry.repository.MemberRepository;

/**
 * @author Rian Rivaldo
 */
public class MemberRepositoryImpl implements MemberRepository {

	private final MemberDao dao = new MemberDaoImpl();

	@Override
	public Member get(Member data) throws Exception {
		return dao.get(data);
	}

	@Override
	public void add(Member data) throws Exception {
		checkBeforeAdd(data, () -> dao.insert(data));
	}

}
