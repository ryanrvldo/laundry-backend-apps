package com.lawencon.laundry.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.DetailTransactionDao;
import com.lawencon.laundry.entity.DetailTransaction;
import com.lawencon.laundry.service.DetailService;

/**
 * @author Rian Rivaldo
 */
@Service
public class DetailServiceImpl implements DetailService {

  @Autowired
  @Qualifier("dtl-trx-nq")
  private DetailTransactionDao detailDao;

  @Override
  public void createDetail(DetailTransaction detail) throws Exception {
	Objects.requireNonNull(detail, () -> "Detail transaction data must be submitted.");
	Objects.requireNonNull(detail.getHeaderTransaction(), "Header of detail transaction data must be submitted.");
	Objects.requireNonNull(detail.getItem(), "Item data must be submitted.");
	Objects.requireNonNull(detail.getQuantity(), "Quantity data must be submitted.");
	Optional.ofNullable(detail.getHeaderTransaction().getId())
	    .orElseThrow(() -> new IllegalArgumentException("Header id is not valid."));
	detailDao.insert(detail);
  }

  @Override
  public List<DetailTransaction> getAllDetail() throws Exception {
	List<DetailTransaction> detailList = detailDao.findAll();
	if (detailList.isEmpty()) throw new NullPointerException("There is no transaction yet.");
	return detailList;
  }

  @Override
  public List<DetailTransaction> getAllDetailByHeaderId(Long id) throws Exception {
	Optional.ofNullable(id)
	    .orElseThrow(() -> new IllegalArgumentException("Header id is not valid."));
	List<DetailTransaction> detailList = detailDao.findAllByHeaderId(id);
	if (detailList.isEmpty()) throw new NullPointerException("There is no transaction yet.");
	return detailList;
  }

}
