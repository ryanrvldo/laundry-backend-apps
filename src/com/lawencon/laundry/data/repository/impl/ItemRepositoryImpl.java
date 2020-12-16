package com.lawencon.laundry.data.repository.impl;

import com.lawencon.laundry.data.dao.ItemDao;
import com.lawencon.laundry.data.dao.impl.ItemDaoImpl;
import com.lawencon.laundry.data.entity.Item;
import com.lawencon.laundry.data.repository.ItemRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ItemRepositoryImpl implements ItemRepository {

	private final ItemDao dao = new ItemDaoImpl();

	@Override
	public Item get(Item item) throws Exception {
		return dao.getEntity(item);
	}

	@Override
	public Item add(Item item) throws Exception {
		return dao.addEntity(item);
	}

}
