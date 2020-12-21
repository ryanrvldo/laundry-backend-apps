package com.lawencon.laundry.service.impl;

import com.lawencon.laundry.constant.LaundryServices;
import com.lawencon.laundry.entity.*;
import com.lawencon.laundry.repository.*;
import com.lawencon.laundry.service.LaundryTransactionService;
import com.lawencon.laundry.util.UserSessionCache;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author Rian Rivaldo
 */
public class LaundryTransactionServiceImpl implements LaundryTransactionService {

	private final TransactionTemplate transactionTemplate;
	private final UserSessionCache userSessionCache;
	private final LaundryServiceRepository laundryServiceRepository;
	private final ItemRepository itemRepository;
	private final ProfileRepository profileRepository;
	private final CustomerRepository customerRepository;
	private final HeaderTransactionRepository headerRepository;
	private final DetailTransactionRepository detailRepository;

	public LaundryTransactionServiceImpl(TransactionTemplate transactionTemplate, UserSessionCache userSessionCache, LaundryServiceRepository laundryServiceRepository, ItemRepository itemRepository, ProfileRepository profileRepository, CustomerRepository customerRepository,
	                                     HeaderTransactionRepository headerRepository, DetailTransactionRepository detailRepository) {
		this.transactionTemplate = transactionTemplate;
		this.userSessionCache = userSessionCache;
		this.laundryServiceRepository = laundryServiceRepository;
		this.itemRepository = itemRepository;
		this.profileRepository = profileRepository;
		this.customerRepository = customerRepository;
		this.headerRepository = headerRepository;
		this.detailRepository = detailRepository;
	}

	@Override
	public LaundryServices getLaundryServiceByCode(String code) throws Exception {
		if (code.equalsIgnoreCase(LaundryServices.UNIT_LAUNDRY.getCode())) {
			return LaundryServices.UNIT_LAUNDRY;
		} else if (code.equalsIgnoreCase(LaundryServices.KILOS_LAUNDRY.getCode())) {
			return LaundryServices.KILOS_LAUNDRY;
		} else {
			throw new Exception("Invalid code.");
		}
	}

	@Override
	public ItemService getItemServiceByCode(String code) throws Exception {
		Optional.ofNullable(code)
				.orElseThrow(() -> new Exception("Type code must be not empty!"));

		ItemService itemService = new ItemService();
		itemService.setCode(code);
		return transactionTemplate.execute(val -> {
			try {
				return laundryServiceRepository.get(itemService);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public Item getLaundryItemByCode(Item item) throws Exception {
		Optional.ofNullable(item.getCode())
				.orElseThrow(() -> new Exception("Item code must be not empty!"));
		return transactionTemplate.execute(val -> {
			try {
				Item selectedItem = itemRepository.get(item);
				item.setId(selectedItem.getId());
				return item;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public void addLaundryTransaction(HeaderTransaction header, List<DetailTransaction> detailList) throws Exception {
		if (detailList.isEmpty()) {
			throw new Exception("Detail transaction list must be not empty!");
		}

		User user = Optional.ofNullable(userSessionCache.getActiveUser())
				.orElseThrow(() -> new IllegalStateException("There is no validated user! Something wrong here."));
		header.setUser(user);
		header.setReceiptNumber(String.format("TRX%d", System.currentTimeMillis()));

		transactionTemplate.executeWithoutResult(val -> {
			try {
				profileRepository.add(header.getCustomer().getProfile());
				customerRepository.add(header.getCustomer());
				if (header.getTotalCost().equals(BigDecimal.ZERO)) {
					BigDecimal totalCost = BigDecimal.ZERO;
					for (DetailTransaction detail : detailList) {
						BigDecimal cost = detail.getItem().getItemService().getCost();
						totalCost = totalCost.add(cost.multiply(BigDecimal.valueOf(detail.getQuantity())));
					}
					header.setTotalCost(totalCost);
				}
				headerRepository.add(header);

				for (DetailTransaction detail : detailList) {
					detail.setHeaderTransaction(header);
					detailRepository.add(detail);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

}
