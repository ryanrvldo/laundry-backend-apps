package com.lawencon.laundry.dao.jpaImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.HeaderTransactionDao;
import com.lawencon.laundry.entity.HeaderTransaction;
import com.lawencon.laundry.repository.HeaderTransactionRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("hdr-trx-jpa")
public class HeaderTransactionDaoJpaImpl implements HeaderTransactionDao {

  @Autowired
  private HeaderTransactionRepository repository;

  @Override
  public void insert(HeaderTransaction data) throws Exception {
	repository.save(data);
  }

  @Override
  public List<HeaderTransaction> findAll() throws Exception {
	List<HeaderTransaction> headerList = new ArrayList<>();
	repository.findAllHeader()
	    .forEach(objArr -> headerList.add(setupHeaderTransaction(objArr)));
	return headerList;
  }

  @Override
  public HeaderTransaction findByReceiptNumber(String receiptNumber) throws Exception {
	Object[] objArr = repository.findByReceiptNumber(receiptNumber);
	return setupHeaderTransaction(objArr);
  }

}
