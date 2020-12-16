package com.lawencon.laundry.service.impl;

import com.lawencon.laundry.data.entity.Item;
import com.lawencon.laundry.data.repository.ItemRepository;
import com.lawencon.laundry.data.repository.impl.ItemRepositoryImpl;
import com.lawencon.laundry.service.LaundryItemService;

import java.util.List;
import java.util.Optional;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LaundryItemServiceImpl implements LaundryItemService {

	private final ItemRepository itemRepository = new ItemRepositoryImpl();

	@Override
	public void addAllItem(List<Item> items) throws Exception {
		if (items.isEmpty()) {
			throw new NullPointerException("Item list is empty!");
		}

		for (Item item : items) {
			Item checkedItem = itemRepository.get(item);
			if (checkedItem == null) {
				Optional.ofNullable(itemRepository.add(item))
						.orElseThrow(() -> new Exception("Failed to add item to database"));
			}
		}
	}

}
