package com.lawencon.laundry.repository.impl;

import com.lawencon.laundry.dao.HeaderTransactionDao;
import com.lawencon.laundry.entity.HeaderTransaction;
import com.lawencon.laundry.repository.HeaderTransactionRepository;

/**
 * @author Rian Rivaldo
 */
public class HeaderTransactionRepositoryImpl implements HeaderTransactionRepository {

	private final HeaderTransactionDao dao;

	public HeaderTransactionRepositoryImpl(HeaderTransactionDao dao) {
		this.dao = dao;
	}

	@Override
	public HeaderTransaction get(HeaderTransaction data) throws Exception {
		return dao.get(data);
	}

	@Override
	public void add(HeaderTransaction data) throws Exception {
		dao.insert(data);
	}

}
