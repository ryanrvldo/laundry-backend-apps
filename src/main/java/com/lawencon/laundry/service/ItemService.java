package com.lawencon.laundry.service;

import com.lawencon.laundry.entity.Item;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public interface ItemService {

	com.lawencon.laundry.entity.ItemService checkLaundryService(String code) throws Exception;

	void addAllItem(List<Item> items) throws Exception;

}
