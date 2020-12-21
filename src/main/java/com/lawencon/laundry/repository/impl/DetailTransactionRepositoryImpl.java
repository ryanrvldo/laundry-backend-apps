package com.lawencon.laundry.repository.impl;

import com.lawencon.laundry.dao.DetailTransactionDao;
import com.lawencon.laundry.entity.DetailTransaction;
import com.lawencon.laundry.repository.DetailTransactionRepository;

/**
 * @author Rian Rivaldo
 */
public class DetailTransactionRepositoryImpl implements DetailTransactionRepository {

	private final DetailTransactionDao dao;

	public DetailTransactionRepositoryImpl(DetailTransactionDao dao) {
		this.dao = dao;
	}

	@Override
	public DetailTransaction get(DetailTransaction data) throws Exception {
		return dao.get(data);
	}

	@Override
	public void add(DetailTransaction data) throws Exception {
		dao.insert(data);
	}

}
