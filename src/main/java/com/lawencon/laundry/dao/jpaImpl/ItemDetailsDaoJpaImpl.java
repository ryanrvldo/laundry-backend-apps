package com.lawencon.laundry.dao.jpaImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.ItemDetailsDao;
import com.lawencon.laundry.entity.ItemDetails;
import com.lawencon.laundry.repository.ItemDetailsRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("item-dtl-jpa")
public class ItemDetailsDaoJpaImpl implements ItemDetailsDao {

  @Autowired
  private ItemDetailsRepository repository;

  @Override
  public void insert(ItemDetails data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(ItemDetails data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(ItemDetails data) throws Exception {
	repository.deleteById(data.getId());
  }

  @Override
  public List<ItemDetails> findAll() throws Exception {
	return repository.findAll();
  }

  @Override
  public ItemDetails findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public ItemDetails findByCode(String code) throws Exception {
	return repository.findByCode(code);
  }

}
