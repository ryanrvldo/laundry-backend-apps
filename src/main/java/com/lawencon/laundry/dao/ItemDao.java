package com.lawencon.laundry.dao;

import java.math.BigDecimal;

import com.lawencon.laundry.entity.Item;
import com.lawencon.laundry.entity.ItemDetails;
import com.lawencon.laundry.entity.Services;

/**
 * @author Rian Rivaldo
 */
public interface ItemDao extends BaseDao<Item> {

  Item findById(Long id) throws Exception;

  default Item setupItem(Object[] objArr) {
	Item item = new Item();
	item.setId(Long.parseLong(objArr[0].toString()));
	item.setCost((BigDecimal) objArr[1]);

	ItemDetails details = new ItemDetails();
	details.setId(Long.parseLong(objArr[2].toString()));
	details.setCode((String) objArr[3]);
	details.setName((String) objArr[4]);
	item.setItemDetails(details);

	Services service = new Services();
	service.setId(Long.parseLong(objArr[5].toString()));
	service.setCode((String) objArr[6]);
	service.setName((String) objArr[7]);
	service.setHourDuration(Long.parseLong(objArr[8].toString()));
	item.setServices(service);

	return item;
  }
}
