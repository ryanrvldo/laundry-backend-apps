package com.lawencon.laundry.dao.hibernate.nativeImpl;

import com.lawencon.laundry.dao.HeaderTransactionDao;
import com.lawencon.laundry.entity.HeaderTransaction;
import com.lawencon.laundry.error.NotImplementedException;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class HeaderTransactionDaoImpl extends BaseDaoImpl<HeaderTransaction> implements HeaderTransactionDao {

	public HeaderTransactionDaoImpl() {
		super(HeaderTransaction.class);
	}

	@Override
	public HeaderTransaction get(HeaderTransaction data) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_r_hdr_transaction hdr ",
				"WHERE lower(hdr.receipt_number) = lower(?1) ");
		return getSingleResultWithQuery(sql, data.getReceiptNumber());
	}

	@Override
	public void insert(HeaderTransaction data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_r_hdr_transaction (receipt_number, customer_id, start_date, total_cost, total_weight, user_id)",
				"VALUES (?1, ?2, ?3, ?4, ?5, ?6) ",
				"RETURNING id");
		Long id = insertWithReturnId(sql,
				data.getReceiptNumber(),
				BigInteger.valueOf(data.getCustomer().getId()),
				data.getStartDate(),
				data.getTotalCost(),
				data.getTotalWeight(),
				BigInteger.valueOf(data.getUser().getId()));
		data.setId(id);
	}

	@Override
	public void update(HeaderTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(HeaderTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<HeaderTransaction> getAll() throws Exception {
		throw new NotImplementedException();
	}

}
