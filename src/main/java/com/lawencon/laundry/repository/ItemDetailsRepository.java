package com.lawencon.laundry.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.entity.ItemDetails;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface ItemDetailsRepository extends JpaRepository<ItemDetails, Long> {

  @Modifying
  @Override
  @Query(value = "DELETE FROM tb_m_item_dtl WHERE id = ?1 ", nativeQuery = true)
  void deleteById(Long id);

  @Override
  @Query(value = "SELECT * FROM tb_m_item_dtl", nativeQuery = true)
  List<ItemDetails> findAll();

  @Override
  @Query(value = "SELECT * FROM tb_m_item_dtl WHERE id = ?1 ", nativeQuery = true)
  Optional<ItemDetails> findById(Long id);

  @Query(value = "SELECT * FROM tb_m_item_dtl WHERE lower(code) = lower(?1) ", nativeQuery = true)
  ItemDetails findByCode(String code);

}
