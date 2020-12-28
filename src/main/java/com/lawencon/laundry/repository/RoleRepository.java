package com.lawencon.laundry.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.entity.Role;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  @Override
  @Query(value = "SELECT * FROM tb_m_role WHERE id = ?1 ", nativeQuery = true)
  Optional<Role> findById(Long id);

  @Query(value = "SELECT * FROM tb_m_role WHERE lower(code) = lower(?1) ", nativeQuery = true)
  Role findByCode(String code);

  @Override
  @Query(value = "SELECT * FROM tb_m_role ", nativeQuery = true)
  List<Role> findAll();

  @Modifying
  @Query(value = "DELETE FROM tb_m_role WHERE lower(code) = lower(?1) ", nativeQuery = true)
  void deleteByCode(String code);
}
