package com.lawencon.laundry.repository.impl;

import com.lawencon.laundry.dao.ItemDao;
import com.lawencon.laundry.entity.Item;
import com.lawencon.laundry.repository.ItemRepository;

/**
 * @author Rian Rivaldo
 */
public class ItemRepositoryImpl implements ItemRepository {

	private final ItemDao dao;

	public ItemRepositoryImpl(ItemDao dao) {
		this.dao = dao;
	}

	@Override
	public Item get(Item data) throws Exception {
		return dao.get(data);
	}

	@Override
	public void add(Item data) throws Exception {
		checkBeforeAdd(data, () -> dao.insert(data));
	}

}
