package com.lawencon.laundry.dao.jpaImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.ProfileDao;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.repository.ProfileRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("profile-jpa")
public class ProfileDaoJpaImpl implements ProfileDao {

  @Autowired
  private ProfileRepository repository;

  @Override
  public void insert(Profile data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Profile data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Profile data) throws Exception {
	repository.deleteById(data.getId());
  }

  @Override
  public List<Profile> findAll() throws Exception {
	return repository.findAll();
  }

  @Override
  public Profile findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public Profile findByEmail(String email) throws Exception {
	return repository.findByEmail(email);
  }

}
