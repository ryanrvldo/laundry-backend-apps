package com.lawencon.laundry.data.repository.impl;

import com.lawencon.laundry.data.dao.HeaderTransactionDao;
import com.lawencon.laundry.data.dao.impl.HeaderTransactionDaoImpl;
import com.lawencon.laundry.data.entity.HeaderTransaction;
import com.lawencon.laundry.data.repository.HeaderTransactionRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class HeaderTransactionRepositoryImpl implements HeaderTransactionRepository {

	private final HeaderTransactionDao dao = new HeaderTransactionDaoImpl();

	@Override
	public HeaderTransaction get(HeaderTransaction item) throws Exception {
		return dao.getEntity(item);
	}

	@Override
	public HeaderTransaction add(HeaderTransaction item) throws Exception {
		return dao.addEntity(item);
	}

}
