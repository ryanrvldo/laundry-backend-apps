package com.lawencon.laundry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lawencon.laundry.entity.DetailTransaction;

/**
 * @author Rian Rivaldo
 */
public interface DetailTransactionRepository extends JpaRepository<DetailTransaction, Long> {

  String getDetailsNativeQuery = "SELECT dtl.id, dtl.quantity, dtl.finish_date, dtl.item_id FROM tb_r_dtl_transaction ";

  @Query(value = getDetailsNativeQuery, nativeQuery = true)
  List<Object[]> findAllDetail();

  @Query(value = getDetailsNativeQuery + "WHERE hdr_id = ?1", nativeQuery = true)
  List<Object[]> findAllByHeaderId(Long id);

}
