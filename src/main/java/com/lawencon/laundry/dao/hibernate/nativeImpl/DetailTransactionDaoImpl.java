package com.lawencon.laundry.dao.hibernate.nativeImpl;

import com.lawencon.laundry.dao.DetailTransactionDao;
import com.lawencon.laundry.entity.DetailTransaction;
import com.lawencon.laundry.entity.HeaderTransaction;
import com.lawencon.laundry.entity.Item;
import com.lawencon.laundry.error.NotImplementedException;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class DetailTransactionDaoImpl extends BaseDaoImpl<DetailTransaction> implements DetailTransactionDao {

	public DetailTransactionDaoImpl() {
		super(DetailTransaction.class);
	}

	@Override
	public DetailTransaction get(DetailTransaction data) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_r_dtl_transaction ",
				"WHERE id = ?1 ");
		return getSingleResultWithQuery(sql, BigInteger.valueOf(data.getId()));
	}

	@Override
	public void insert(DetailTransaction data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_r_dtl_transaction (hdr_id, item_id, quantity, finish_date) ",
				"VALUES (?1, ?2, ?3, ?4) ");
		insertWithNoReturnQuery(sql,
				BigInteger.valueOf(data.getHeaderTransaction().getId()),
				BigInteger.valueOf(data.getItem().getId()),
				data.getQuantity(),
				data.getFinishDate());
	}

	@Override
	public void update(DetailTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(DetailTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<DetailTransaction> getAll() throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<DetailTransaction> getAllByHeaderId(HeaderTransaction header) throws Exception {
		String sql = buildQueryOf(
				"SELECT dtl.id, dtl.item_id, dtl.quantity, dtl.finish_date, dtl.hdr_id, hdr.receipt_number ",
				"FROM tb_r_dtl_transaction dtl ",
				"INNER JOIN tb_r_hdr_transaction hdr ON hdr.id = dtl.hdr_id ",
				"WHERE hdr_id = ?1 "
		);
		List<DetailTransaction> transactionList = new ArrayList<>();
		getAllWithQuery(sql, objArr -> {
			DetailTransaction detail = new DetailTransaction();
			detail.setId(Long.parseLong(objArr[0].toString()));

			Item item = new Item();
			item.setId(Long.parseLong(objArr[1].toString()));

			detail.setItem(item);
			detail.setQuantity((Integer) objArr[2]);
			detail.setFinishDate((LocalDateTime) objArr[3]);

			HeaderTransaction headerTransaction = new HeaderTransaction();
			header.setId(Long.parseLong(objArr[4].toString()));
			header.setReceiptNumber((String) objArr[5]);
			detail.setHeaderTransaction(headerTransaction);

			transactionList.add(detail);
		});
		return transactionList;
	}

}
