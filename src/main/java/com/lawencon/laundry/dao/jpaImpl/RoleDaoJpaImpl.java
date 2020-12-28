package com.lawencon.laundry.dao.jpaImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.RoleDao;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.repository.RoleRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("role-jpa")
public class RoleDaoJpaImpl implements RoleDao {

  @Autowired
  private RoleRepository repository;

  @Override
  public void insert(Role data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Role data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Role data) throws Exception {
	repository.deleteByCode(data.getCode());
  }

  @Override
  public List<Role> findAll() throws Exception {
	return repository.findAll();
  }

  @Override
  public Role findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public Role findByCode(String code) throws Exception {
	return repository.findByCode(code);
  }

}
