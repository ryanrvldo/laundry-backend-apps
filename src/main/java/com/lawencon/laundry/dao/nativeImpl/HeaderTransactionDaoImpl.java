package com.lawencon.laundry.dao.nativeImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.HeaderTransactionDao;
import com.lawencon.laundry.entity.HeaderTransaction;

/**
 * @author Rian Rivaldo
 */
@Repository("hdr-trx-nq")
public class HeaderTransactionDaoImpl extends BaseDaoImpl<HeaderTransaction> implements HeaderTransactionDao {

  public HeaderTransactionDaoImpl() {
	super(HeaderTransaction.class);
  }

  private final String getHeaderQuery = buildQueryOf(
      "SELECT hdr.id, hdr.receipt_number, hdr.start_date, hdr.total_cost, hdr.total_weight, hdr.customer_id, ",
      "c.profile_id, p.full_name, p.phone, hdr.user_id, u.username ",
      "FROM tb_r_hdr_transaction hdr INNER JOIN tb_m_customer c ON c.id = hdr.customer_id ",
      "INNER JOIN tb_m_profile p ON p.id = c.profile_id ",
      "INNER JOIN tb_m_user u ON u.id = hdr.user_id ");

  @Override
  public void insert(HeaderTransaction data) throws Exception {
	String sql = buildQueryOf(
	    "INSERT INTO tb_r_hdr_transaction (receipt_number, customer_id, start_date, total_cost, total_weight, user_id)",
	    "VALUES (?1, ?2, ?3, ?4, ?5, ?6) ",
	    "RETURNING id");
	Long id = insertWithReturnId(sql,
	    data.getReceiptNumber(),
	    BigInteger.valueOf(data.getCustomer().getId()),
	    data.getStartDate(),
	    data.getTotalCost(),
	    data.getTotalWeight(),
	    BigInteger.valueOf(data.getUser().getId()));
	data.setId(id);
  }

  @Override
  public List<HeaderTransaction> findAll() throws Exception {
	List<HeaderTransaction> headerList = new ArrayList<>();
	getAllWithQuery(getHeaderQuery, objArr -> headerList.add(setupHeaderTransaction(objArr)));
	return headerList;
  }

  @Override
  public HeaderTransaction findByReceiptNumber(String receiptNumber) throws Exception {
	String sql = buildQueryOf(getHeaderQuery, "WHERE receipt_number = ?1");
	Object obj = getEntityManager().createNativeQuery(sql)
	    .setParameter(1, receiptNumber)
	    .getSingleResult();
	return setupHeaderTransaction((Object[]) obj);
  }

}
