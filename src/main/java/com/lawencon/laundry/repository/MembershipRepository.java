package com.lawencon.laundry.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.entity.Membership;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

  @Modifying
  @Override
  @Query(value = "DELETE FROM tb_m_membership WHERE id = ?1 ", nativeQuery = true)
  void deleteById(Long id);

  @Override
  @Query(value = "SELECT * FROM tb_m_membership", nativeQuery = true)
  List<Membership> findAll();

  @Override
  @Query(value = "SELECT * FROM tb_m_membership WHERE id = ?1 ", nativeQuery = true)
  Optional<Membership> findById(Long id);

  @Query(value = "SELECT * FROM tb_m_membership WHERE lower(code) = lower(?1) ", nativeQuery = true)
  Membership findByCode(String code);

}
