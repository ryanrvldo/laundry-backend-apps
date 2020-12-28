package com.lawencon.laundry.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.lawencon.laundry.entity.Customer;
import com.lawencon.laundry.entity.HeaderTransaction;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.entity.User;

/**
 * @author Rian Rivaldo
 */
public interface HeaderTransactionDao extends BaseDao<HeaderTransaction> {

  HeaderTransaction findByReceiptNumber(String receiptNumber) throws Exception;

  default HeaderTransaction setupHeaderTransaction(Object[] objArr) {
	HeaderTransaction header = new HeaderTransaction();
	header.setId(Long.parseLong(objArr[0].toString()));
	header.setReceiptNumber((String) objArr[1]);
	header.setStartDate(((Timestamp) objArr[2]).toLocalDateTime());
	header.setTotalCost((BigDecimal) objArr[3]);
	header.setTotalWeight((Double) objArr[4]);

	Customer customer = new Customer();
	customer.setId(Long.parseLong(objArr[5].toString()));

	Profile profile = new Profile();
	profile.setId(Long.parseLong(objArr[6].toString()));
	profile.setFullName((String) objArr[7]);
	profile.setPhone((String) objArr[8]);
	customer.setProfile(profile);
	header.setCustomer(customer);

	User user = new User();
	user.setId(Long.parseLong(objArr[9].toString()));
	user.setUsername((String) objArr[10]);
	header.setUser(user);

	return header;
  }

  @Override
  default void update(HeaderTransaction data) throws Exception {
	throw new IllegalAccessException();
  }

  @Override
  default void delete(HeaderTransaction data) throws Exception {
	throw new IllegalAccessException();
  }

}
