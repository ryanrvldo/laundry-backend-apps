package com.lawencon.laundry.service;

import com.lawencon.laundry.data.entity.Item;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface LaundryItemService {

	void addAllItem(List<Item> items) throws Exception;

}
