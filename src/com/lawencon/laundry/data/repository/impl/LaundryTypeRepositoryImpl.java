package com.lawencon.laundry.data.repository.impl;

import com.lawencon.laundry.data.dao.LaundryTypeDao;
import com.lawencon.laundry.data.dao.impl.LaundryTypeDaoImpl;
import com.lawencon.laundry.data.entity.LaundryType;
import com.lawencon.laundry.data.repository.LaundryTypeRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LaundryTypeRepositoryImpl implements LaundryTypeRepository {

	private final LaundryTypeDao dao = new LaundryTypeDaoImpl();

	@Override
	public LaundryType get(LaundryType item) throws Exception {
		return dao.getEntity(item);
	}

	@Override
	public LaundryType add(LaundryType item) throws Exception {
		return dao.addEntity(item);
	}

}
