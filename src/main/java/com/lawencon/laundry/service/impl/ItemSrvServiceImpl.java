package com.lawencon.laundry.service.impl;

import com.lawencon.laundry.entity.ItemService;
import com.lawencon.laundry.repository.LaundryServiceRepository;
import com.lawencon.laundry.service.ItemSrvService;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class ItemSrvServiceImpl implements ItemSrvService {

	private final TransactionTemplate transactionTemplate;
	private final LaundryServiceRepository laundryServiceRepository;

	public ItemSrvServiceImpl(TransactionTemplate transactionTemplate, LaundryServiceRepository laundryServiceRepository) {
		this.transactionTemplate = transactionTemplate;
		this.laundryServiceRepository = laundryServiceRepository;
	}

	@Override
	public void addAllService(List<ItemService> serviceList) throws Exception {
		if (serviceList.isEmpty()) {
			throw new Exception("Laundry type list is empty!");
		}

		transactionTemplate.executeWithoutResult(val -> {
			for (ItemService service : serviceList) {
				try {
					laundryServiceRepository.add(service);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

}
