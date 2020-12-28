package com.lawencon.laundry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lawencon.laundry.entity.HeaderTransaction;

/**
 * @author Rian Rivaldo
 */
public interface HeaderTransactionRepository extends JpaRepository<HeaderTransaction, Long> {

  String getHeaderQuery = "SELECT hdr.id, hdr.receipt_number, hdr.start_date, hdr.total_cost, hdr.total_weight, hdr.customer_id, "
      + "c.profile_id, p.full_name, p.phone, hdr.user_id, u.username "
      + "FROM tb_r_hdr_transaction hdr INNER JOIN tb_m_customer c ON c.id = hdr.customer_id "
      + "INNER JOIN tb_m_profile p ON p.id = c.profile_id "
      + "INNER JOIN tb_m_user u ON u.id = hdr.user_id ";

  @Query(value = getHeaderQuery, nativeQuery = true)
  List<Object[]> findAllHeader();

  @Query(value = getHeaderQuery + "WHERE receipt_number = ?1", nativeQuery = true)
  Object[] findByReceiptNumber(String receiptNumber);

}
