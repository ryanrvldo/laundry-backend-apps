package com.lawencon.laundry.service.impl;

import com.lawencon.laundry.data.entity.LaundryType;
import com.lawencon.laundry.data.repository.LaundryTypeRepository;
import com.lawencon.laundry.data.repository.impl.LaundryTypeRepositoryImpl;
import com.lawencon.laundry.service.LaundryTypeService;

import java.util.List;
import java.util.Optional;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LaundryTypeServiceImpl implements LaundryTypeService {

	private final LaundryTypeRepository laundryTypeRepository = new LaundryTypeRepositoryImpl();

	@Override
	public void addAllType(List<LaundryType> types) throws Exception {
		if (types.isEmpty()) {
			throw new NullPointerException("Laundry type list is empty!");
		}

		for (LaundryType type : types) {
			LaundryType checkedType = laundryTypeRepository.get(type);
			if (checkedType == null) {
				Optional.ofNullable(laundryTypeRepository.add(type))
						.orElseThrow(() -> new Exception("Failed to add laundry type to database"));
			}
		}
	}

}
