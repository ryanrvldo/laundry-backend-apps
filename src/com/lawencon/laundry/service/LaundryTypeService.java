package com.lawencon.laundry.service;

import com.lawencon.laundry.data.entity.LaundryType;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface LaundryTypeService {

	void addAllType(List<LaundryType> type) throws Exception;

}
