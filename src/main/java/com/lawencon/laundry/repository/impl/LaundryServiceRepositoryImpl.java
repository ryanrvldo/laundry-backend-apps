package com.lawencon.laundry.repository.impl;

import com.lawencon.laundry.dao.ItemServiceDao;
import com.lawencon.laundry.entity.ItemService;
import com.lawencon.laundry.repository.LaundryServiceRepository;

/**
 * @author Rian Rivaldo
 */
public class LaundryServiceRepositoryImpl implements LaundryServiceRepository {

	private final ItemServiceDao dao;

	public LaundryServiceRepositoryImpl(ItemServiceDao dao) {
		this.dao = dao;
	}

	@Override
	public ItemService get(ItemService data) throws Exception {
		return dao.get(data);
	}

	@Override
	public void add(ItemService data) throws Exception {
		checkBeforeAdd(data, () -> dao.insert(data));
	}
}
