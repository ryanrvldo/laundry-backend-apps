package com.lawencon.laundry.dao.jpaImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.DetailTransactionDao;
import com.lawencon.laundry.entity.DetailTransaction;
import com.lawencon.laundry.repository.DetailTransactionRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("dtl-trx-jpa")
public class DetailTransactionDaoJpaImpl implements DetailTransactionDao {

  @Autowired
  private DetailTransactionRepository repository;

  @Override
  public void insert(DetailTransaction data) throws Exception {
	repository.save(data);
  }

  @Override
  public List<DetailTransaction> findAll() throws Exception {
	List<DetailTransaction> detailList = new ArrayList<>();
	repository.findAllDetail()
	    .forEach(objArr -> detailList.add(setupDetail(objArr)));
	return detailList;
  }

  @Override
  public List<DetailTransaction> findAllByHeaderId(Long id) throws Exception {
	List<DetailTransaction> detailList = new ArrayList<>();
	repository.findAllByHeaderId(id)
	    .forEach(objArr -> detailList.add(setupDetail(objArr)));
	return detailList;
  }

}
