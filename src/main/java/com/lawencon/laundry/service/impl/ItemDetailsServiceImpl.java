package com.lawencon.laundry.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.ItemDetailsDao;
import com.lawencon.laundry.entity.ItemDetails;
import com.lawencon.laundry.error.DataIsNotExistsException;
import com.lawencon.laundry.service.ItemDetailsService;
import com.lawencon.laundry.util.ValidationUtils;

/**
 * @author Rian Rivaldo
 */
@Service
public class ItemDetailsServiceImpl implements ItemDetailsService {

  @Autowired
  private ValidationUtils validationUtils;

  @Autowired
  @Qualifier("item-dtl-jpa")
  private ItemDetailsDao itemDetailsDao;

  @Override
  public void createItemDetails(ItemDetails item) throws Exception {
	Objects.requireNonNull(item, () -> "Item data must be submitted.");
	validationUtils.validateString(item.getCode(), item.getName());
	itemDetailsDao.insert(item);
  }

  @Transactional
  @Override
  public void updateItemDetails(ItemDetails item) throws Exception {
	Long id = Optional.ofNullable(item)
	    .map(i -> i.getId())
	    .orElseThrow(() -> new NullPointerException("Item id must be submitted"));
	validationUtils.validateEntityId(id, () -> getItemDetailsById(id));
	validationUtils.validateString(item.getCode(), item.getName());
	itemDetailsDao.update(item);
  }

  @Transactional
  @Override
  public void deleteItemDetailsById(Long id) throws Exception {
	Objects.requireNonNull(id, () -> "Item id must be submitted.");
	validationUtils.validateEntityId(id, null);
	ItemDetails item = new ItemDetails();
	item.setId(id);
	itemDetailsDao.delete(item);
  }

  @Override
  public List<ItemDetails> getAll() throws Exception {
	List<ItemDetails> itemList = itemDetailsDao.findAll();
	if (itemList.isEmpty()) {
	  throw new NullPointerException("Item details data is empty.");
	}
	return itemList;
  }

  @Override
  public ItemDetails getItemDetailsById(Long id) throws Exception {
	return validationUtils.validateEntityId(id, () -> itemDetailsDao.findById(id));
  }

  @Override
  public ItemDetails getItemDetailsByCode(String code) throws Exception {
	Objects.requireNonNull(code, () -> "Item code must be submitted.");
	return Optional.ofNullable(validationUtils.validateGet(() -> itemDetailsDao.findByCode(code)))
	    .orElseThrow(() -> new DataIsNotExistsException(code));
  }

}
