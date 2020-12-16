package com.lawencon.laundry.service;

import com.lawencon.laundry.data.entity.DetailTransaction;
import com.lawencon.laundry.data.entity.HeaderTransaction;
import com.lawencon.laundry.data.entity.Item;
import com.lawencon.laundry.data.entity.LaundryType;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface LaundryTransactionService {

	LaundryType getLaundryTypeByCode(String code) throws Exception;

	Item getLaundryItemByCode(String code) throws Exception;

	void addLaundryTransaction(HeaderTransaction header, List<DetailTransaction> detailList) throws Exception;

}
