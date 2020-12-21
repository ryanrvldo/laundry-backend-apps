package com.lawencon.laundry.dao;

import com.lawencon.laundry.entity.DetailTransaction;
import com.lawencon.laundry.entity.HeaderTransaction;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public interface DetailTransactionDao extends BaseDao<DetailTransaction> {

	List<DetailTransaction> getAllByHeaderId(HeaderTransaction header) throws Exception;

}
