package com.lawencon.laundry.dao.jpaImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.ItemDao;
import com.lawencon.laundry.entity.Item;
import com.lawencon.laundry.repository.ItemRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("item-jpa")
public class ItemDaoJpaImpl implements ItemDao {

  @Autowired
  private ItemRepository repository;

  @Override
  public void insert(Item data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Item data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Item data) throws Exception {
	repository.deleteById(data.getId());
  }

  @Override
  public List<Item> findAll() throws Exception {
	List<Item> itemList = new ArrayList<>();
	repository.findAllWithService()
	    .forEach(objArr -> itemList.add(setupItem(objArr)));
	return itemList;
  }

  @Override
  public Item findById(Long id) throws Exception {
	Object obj = repository.findItemById(id);
	return setupItem((Object[]) obj);
  }

}
