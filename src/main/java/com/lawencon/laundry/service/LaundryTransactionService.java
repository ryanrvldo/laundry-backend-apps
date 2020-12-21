package com.lawencon.laundry.service;

import com.lawencon.laundry.constant.LaundryServices;
import com.lawencon.laundry.entity.DetailTransaction;
import com.lawencon.laundry.entity.HeaderTransaction;
import com.lawencon.laundry.entity.Item;
import com.lawencon.laundry.entity.ItemService;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public interface LaundryTransactionService {

	LaundryServices getLaundryServiceByCode(String code) throws Exception;

	ItemService getItemServiceByCode(String code) throws Exception;

	Item getLaundryItemByCode(Item item) throws Exception;

	void addLaundryTransaction(HeaderTransaction header, List<DetailTransaction> detailList) throws Exception;

}
