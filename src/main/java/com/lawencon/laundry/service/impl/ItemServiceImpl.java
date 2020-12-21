package com.lawencon.laundry.service.impl;

import com.lawencon.laundry.entity.Item;
import com.lawencon.laundry.entity.ItemService;
import com.lawencon.laundry.repository.ItemRepository;
import com.lawencon.laundry.repository.LaundryServiceRepository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author Rian Rivaldo
 */
public class ItemServiceImpl implements com.lawencon.laundry.service.ItemService {

	private final TransactionTemplate transactionTemplate;
	private final LaundryServiceRepository laundryServiceRepository;
	private final ItemRepository itemRepository;

	public ItemServiceImpl(TransactionTemplate transactionTemplate, LaundryServiceRepository laundryServiceRepository, ItemRepository itemRepository) {
		this.transactionTemplate = transactionTemplate;
		this.laundryServiceRepository = laundryServiceRepository;
		this.itemRepository = itemRepository;
	}

	@Override
	public ItemService checkLaundryService(String code) throws Exception {
		Optional.ofNullable(code)
				.orElseThrow(() -> new Exception("Service code must be not empty"));
		return transactionTemplate.execute(val -> {
			ItemService itemService = new ItemService();
			itemService.setCode(code);
			try {
				return Optional.ofNullable(laundryServiceRepository.get(itemService))
						.orElseThrow(() -> new RuntimeException("Invalid service code."));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public void addAllItem(List<Item> items) throws Exception {
		if (items.isEmpty()) {
			throw new Exception("Item list is empty!");
		}

		transactionTemplate.executeWithoutResult(val -> {
			for (Item item : items) {
				try {
					itemRepository.add(item);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

}
