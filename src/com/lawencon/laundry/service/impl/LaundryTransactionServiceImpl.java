package com.lawencon.laundry.service.impl;

import com.lawencon.laundry.data.entity.*;
import com.lawencon.laundry.data.repository.*;
import com.lawencon.laundry.data.repository.impl.*;
import com.lawencon.laundry.service.LaundryTransactionService;
import com.lawencon.laundry.util.UserSessionCache;

import java.util.List;
import java.util.Optional;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LaundryTransactionServiceImpl implements LaundryTransactionService {

	private final LaundryTypeRepository laundryTypeRepository = new LaundryTypeRepositoryImpl();
	private final ItemRepository itemRepository = new ItemRepositoryImpl();
	private final ProfileRepository profileRepository = new ProfileRepositoryImpl();
	private final CustomerRepository customerRepository = new CustomerRepositoryImpl();
	private final HeaderTransactionRepository headerRepository = new HeaderTransactionRepositoryImpl();
	private final DetailTransactionRepository detailRepository = new DetailTransactionRepositoryImpl();

	@Override
	public LaundryType getLaundryTypeByCode(String code) throws Exception {
		Optional.ofNullable(code)
				.orElseThrow(() -> new IllegalArgumentException("Type code must be not empty!"));

		LaundryType laundryType = new LaundryType(code, null);
		return Optional.ofNullable(laundryTypeRepository.get(laundryType))
				.orElseThrow(() -> new IllegalArgumentException("Invalid laundry type."));
	}

	@Override
	public Item getLaundryItemByCode(String code) throws Exception {
		Optional.ofNullable(code)
				.orElseThrow(() -> new IllegalArgumentException("Item code must be not empty!"));

		Item item = new Item(code, null);
		return Optional.ofNullable(itemRepository.get(item))
				.orElseThrow(() -> new IllegalArgumentException("Invalid item."));
	}

	@Override
	public void addLaundryTransaction(HeaderTransaction header, List<DetailTransaction> detailList) throws Exception {
		if (detailList.isEmpty()) {
			throw new NullPointerException("Detail transaction list must be not empty!");
		}

		User user = Optional.ofNullable(UserSessionCache.getInstance().getActiveUser())
				.orElseThrow(() -> new IllegalStateException("There is no validated user! Something wrong here."));
		header.setUser(user);

		Profile addedProfile = Optional.ofNullable(profileRepository.add(header.getCustomer().getProfile()))
				.orElseThrow(() -> new Exception("Failed to add customer profile entity to database."));
		header.getCustomer().setProfile(addedProfile);

		Customer addedCustomer = Optional.ofNullable(customerRepository.add(header.getCustomer()))
				.orElseThrow(() -> new Exception("Failed to add customer entity to database."));
		header.setCustomer(addedCustomer);

		HeaderTransaction addedHeader = Optional.ofNullable(headerRepository.add(header))
				.orElseThrow(() -> new Exception("Failed to add header transaction."));

		for (DetailTransaction detail : detailList) {
			detail.setHeaderTransaction(addedHeader);
			Optional.ofNullable(detailRepository.add(detail))
					.orElseThrow(() -> new Exception("Failed to add detail transaction."));
		}

	}

}
