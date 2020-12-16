package com.lawencon.laundry.data.repository.impl;

import com.lawencon.laundry.data.dao.DetailTransactionDao;
import com.lawencon.laundry.data.dao.impl.DetailTransactionDaoImpl;
import com.lawencon.laundry.data.entity.DetailTransaction;
import com.lawencon.laundry.data.repository.DetailTransactionRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class DetailTransactionRepositoryImpl implements DetailTransactionRepository {

	private final DetailTransactionDao dao = new DetailTransactionDaoImpl();

	@Override
	public DetailTransaction get(DetailTransaction item) throws Exception {
		return dao.getEntity(item);
	}

	@Override
	public DetailTransaction add(DetailTransaction item) throws Exception {
		return dao.addEntity(item);
	}

}
