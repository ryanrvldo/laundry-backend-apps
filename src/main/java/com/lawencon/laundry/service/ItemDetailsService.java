package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.entity.ItemDetails;

/**
 * @author Rian Rivaldo
 */

public interface ItemDetailsService {

  void createItemDetails(ItemDetails item) throws Exception;

  void updateItemDetails(ItemDetails item) throws Exception;

  void deleteItemDetailsById(Long id) throws Exception;

  List<ItemDetails> getAll() throws Exception;

  ItemDetails getItemDetailsById(Long id) throws Exception;

  ItemDetails getItemDetailsByCode(String code) throws Exception;

}
