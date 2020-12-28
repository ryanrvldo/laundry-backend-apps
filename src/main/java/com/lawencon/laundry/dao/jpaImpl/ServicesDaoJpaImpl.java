package com.lawencon.laundry.dao.jpaImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.ServicesDao;
import com.lawencon.laundry.entity.Services;
import com.lawencon.laundry.repository.ServicesRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("service-jpa")
public class ServicesDaoJpaImpl implements ServicesDao {

  @Autowired
  private ServicesRepository repository;

  @Override
  public void insert(Services data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Services data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Services data) throws Exception {
	repository.deleteById(data.getId());
  }

  @Override
  public List<Services> findAll() throws Exception {
	return repository.findAll();
  }

  @Override
  public Services findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public Services findByCode(String code) throws Exception {
	return repository.findByCode(code);
  }

}
