package com.lawencon.laundry.dao;

import java.sql.Timestamp;
import java.util.List;

import com.lawencon.laundry.entity.DetailTransaction;
import com.lawencon.laundry.entity.Item;

/**
 * @author Rian Rivaldo
 */
public interface DetailTransactionDao extends BaseDao<DetailTransaction> {

  List<DetailTransaction> findAllByHeaderId(Long id) throws Exception;

  default DetailTransaction setupDetail(Object[] objArr) {
	DetailTransaction detail = new DetailTransaction();
	detail.setId(Long.parseLong(objArr[0].toString()));
	detail.setQuantity((Integer) objArr[1]);
	detail.setFinishDate(((Timestamp) objArr[2]).toLocalDateTime());

	Item item = new Item();
	item.setId(Long.parseLong(objArr[3].toString()));
	detail.setItem(item);

	return detail;
  }

  @Override
  default void update(DetailTransaction data) throws Exception {
	throw new IllegalAccessException();
  }

  @Override
  default void delete(DetailTransaction data) throws Exception {
	throw new IllegalAccessException();
  }

}
