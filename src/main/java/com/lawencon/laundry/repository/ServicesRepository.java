package com.lawencon.laundry.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.entity.Services;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

  @Modifying
  @Override
  @Query(value = "DELETE FROM tb_m_service WHERE id = ?1 ", nativeQuery = true)
  void deleteById(Long id);

  @Override
  @Query(value = "SELECT * FROM tb_m_service", nativeQuery = true)
  List<Services> findAll();

  @Override
  @Query(value = "SELECT * FROM tb_m_service WHERE id = ?1 ", nativeQuery = true)
  Optional<Services> findById(Long id);

  @Query(value = "SELECT * FROM tb_m_service WHERE lower(code) = lower(?1) ", nativeQuery = true)
  Services findByCode(String code);

}
