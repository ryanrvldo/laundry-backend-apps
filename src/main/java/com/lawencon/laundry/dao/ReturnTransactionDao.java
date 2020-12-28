package com.lawencon.laundry.dao;

import com.lawencon.laundry.entity.ReturnTransaction;

/**
 * @author Rian Rivaldo
 */
public interface ReturnTransactionDao extends BaseDao<ReturnTransaction> {

  @Override
  default void update(ReturnTransaction data) throws Exception {
	throw new IllegalAccessException();
  }

  @Override
  default void delete(ReturnTransaction data) throws Exception {
	throw new IllegalAccessException();
  }

}
