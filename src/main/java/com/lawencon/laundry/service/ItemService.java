package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.entity.Item;

/**
 * @author Rian Rivaldo
 */

public interface ItemService {

  void createItem(Item item) throws Exception;

  void updateItem(Item item) throws Exception;

  void deleteItemById(Long id) throws Exception;

  List<Item> getAll() throws Exception;

  Item getItemById(Long id) throws Exception;

}
