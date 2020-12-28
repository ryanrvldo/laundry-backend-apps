package com.lawencon.laundry.service.impl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.ItemDao;
import com.lawencon.laundry.entity.Item;
import com.lawencon.laundry.entity.ItemDetails;
import com.lawencon.laundry.entity.Services;
import com.lawencon.laundry.service.ItemDetailsService;
import com.lawencon.laundry.service.ItemService;
import com.lawencon.laundry.service.ItemServicesService;
import com.lawencon.laundry.util.ValidationUtils;

/**
 * @author Rian Rivaldo
 */
@Service
public class ItemServiceImpl implements ItemService {

  @Autowired
  private ValidationUtils validationUtils;

  @Autowired
  @Qualifier("item-jpa")
  private ItemDao itemDao;

  @Autowired
  private ItemDetailsService detailService;

  @Autowired
  private ItemServicesService itemServicesService;

  @Override
  public void createItem(Item item) throws Exception {
	validateItem(item);
	itemDao.insert(item);
  }

  @Transactional
  @Override
  public void updateItem(Item item) throws Exception {
	validateItem(item);
	itemDao.update(item);
  }

  @Transactional
  @Override
  public void deleteItemById(Long id) throws Exception {
	Item item = getItemById(id);
	itemDao.delete(item);
  }

  @Override
  public List<Item> getAll() throws Exception {
	List<Item> itemList = itemDao.findAll();
	if (itemList.isEmpty()) {
	  throw new NullPointerException("Item data is empty.");
	}
	return itemList;
  }

  @Override
  public Item getItemById(Long id) throws Exception {
	return validationUtils.validateEntityId(id, () -> itemDao.findById(id));
  }

  private void validateItem(Item item) throws Exception {
	Objects.requireNonNull(item, () -> "Item data must be submitted.");
	Objects.requireNonNull(item.getItemDetails().getCode(), () -> "Item detail data must be submitted.");
	Objects.requireNonNull(item.getServices().getCode(), () -> "Item service data must be submitted.");
	Objects.requireNonNull(item.getCost(), () -> "Item cost data must be submitted.");
	ItemDetails details = detailService.getItemDetailsByCode(item.getItemDetails().getCode());
	Services service = itemServicesService.getServiceByCode(item.getServices().getCode());
	item.setItemDetails(details);
	item.setServices(service);
  }

}
