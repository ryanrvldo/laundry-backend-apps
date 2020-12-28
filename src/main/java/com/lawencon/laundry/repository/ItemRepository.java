package com.lawencon.laundry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lawencon.laundry.entity.Item;

/**
 * @author Rian Rivaldo
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

  String getItemWithService = "SELECT i.id, i.cost, i.item_id, dtl.code as dtl_code, dtl.item_name, i.service_id, s.code as s_code, s.service_name, s.hour_duration "
      + "FROM tb_m_item i INNER JOIN tb_m_item_dtl dtl ON dtl.id = i.item_id "
      + "INNER JOIN tb_m_service s ON s.id = i.service_id ";

  @Modifying
  @Override
  @Query(value = "DELETE FROM tb_m_item WHERE id = ?1", nativeQuery = true)
  void deleteById(Long id);

  @Query(value = getItemWithService, nativeQuery = true)
  List<Object[]> findAllWithService();

  @Query(value = getItemWithService + "WHERE i.id = ?1", nativeQuery = true)
  Object findItemById(Long id);
}
