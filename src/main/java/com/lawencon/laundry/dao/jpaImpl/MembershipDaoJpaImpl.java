package com.lawencon.laundry.dao.jpaImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.MembershipDao;
import com.lawencon.laundry.entity.Membership;
import com.lawencon.laundry.repository.MembershipRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("membership-jpa")
public class MembershipDaoJpaImpl implements MembershipDao {

  @Autowired
  private MembershipRepository repository;

  @Override
  public void insert(Membership data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Membership data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Membership data) throws Exception {
	repository.deleteById(data.getId());
  }

  @Override
  public List<Membership> findAll() throws Exception {
	return repository.findAll();
  }

  @Override
  public Membership findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public Membership findByCode(String code) throws Exception {
	return repository.findByCode(code);
  }

}
