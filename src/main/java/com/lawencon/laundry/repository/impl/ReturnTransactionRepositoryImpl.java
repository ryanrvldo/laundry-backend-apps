package com.lawencon.laundry.repository.impl;

import com.lawencon.laundry.dao.ReturnTransactionDao;
import com.lawencon.laundry.entity.ReturnTransaction;
import com.lawencon.laundry.repository.ReturnTransactionRepository;

/**
 * @author Rian Rivaldo
 */
public class ReturnTransactionRepositoryImpl implements ReturnTransactionRepository {

	private final ReturnTransactionDao dao;

	public ReturnTransactionRepositoryImpl(ReturnTransactionDao dao) {
		this.dao = dao;
	}

	@Override
	public ReturnTransaction get(ReturnTransaction data) throws Exception {
		return dao.get(data);
	}

	@Override
	public void add(ReturnTransaction data) throws Exception {
		dao.insert(data);
	}
}
