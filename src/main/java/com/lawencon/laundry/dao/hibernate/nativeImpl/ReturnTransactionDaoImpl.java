package com.lawencon.laundry.dao.hibernate.nativeImpl;

import com.lawencon.laundry.dao.ReturnTransactionDao;
import com.lawencon.laundry.entity.ReturnTransaction;
import com.lawencon.laundry.error.NotImplementedException;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class ReturnTransactionDaoImpl extends BaseDaoImpl<ReturnTransaction> implements ReturnTransactionDao {

	public ReturnTransactionDaoImpl() {
		super(ReturnTransaction.class);
	}

	@Override
	public ReturnTransaction get(ReturnTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void insert(ReturnTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void update(ReturnTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(ReturnTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<ReturnTransaction> getAll() throws Exception {
		throw new NotImplementedException();
	}

}
