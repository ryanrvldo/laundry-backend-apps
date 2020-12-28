package com.lawencon.laundry.dao.nativeImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.DetailTransactionDao;
import com.lawencon.laundry.entity.DetailTransaction;

/**
 * @author Rian Rivaldo
 */
@Repository("dtl-trx-nq")
public class DetailTransactionDaoImpl extends BaseDaoImpl<DetailTransaction> implements DetailTransactionDao {

  public DetailTransactionDaoImpl() {
	super(DetailTransaction.class);
  }

  private final String getDetailsQuery = buildQueryOf(
      "SELECT id, quantity, finish_date, item_id ",
      "FROM tb_r_dtl_transaction ");

  @Override
  public void insert(DetailTransaction data) throws Exception {
	String sql = buildQueryOf(
	    "INSERT INTO tb_r_dtl_transaction (hdr_id, item_id, quantity, finish_date) ",
	    "VALUES (?1, ?2, ?3, ?4) ");
	executeUpdateWithQuery(sql,
	    BigInteger.valueOf(data.getHeaderTransaction().getId()),
	    BigInteger.valueOf(data.getItem().getId()),
	    data.getQuantity(),
	    data.getFinishDate());
  }

  @Override
  public List<DetailTransaction> findAll() throws Exception {
	List<DetailTransaction> detailList = new ArrayList<>();
	getAllWithQuery(getDetailsQuery, objArr -> detailList.add(setupDetail(objArr)));
	return detailList;
  }

  @Override
  public List<DetailTransaction> findAllByHeaderId(Long id) throws Exception {
	String sql = buildQueryOf(getDetailsQuery, "WHERE hdr_id = ?1");
	List<DetailTransaction> transactionList = new ArrayList<>();
	List<?> objList = getEntityManager().createNativeQuery(sql)
	    .setParameter(1, id)
	    .getResultList();
	objList.forEach(val -> {
	  Object[] objArr = (Object[]) val;
	  transactionList.add(setupDetail(objArr));
	});
	return transactionList;
  }

}
