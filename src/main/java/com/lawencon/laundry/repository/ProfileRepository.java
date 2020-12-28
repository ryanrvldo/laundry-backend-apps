package com.lawencon.laundry.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.entity.Profile;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

  @Modifying
  @Override
  @Query(value = "DELETE FROM tb_m_profile WHERE id = ?1", nativeQuery = true)
  void deleteById(Long id);

  @Override
  @Query(value = "SELECT * FROM tb_m_profile WHERE id = ?1", nativeQuery = true)
  Optional<Profile> findById(Long id);

  @Query(value = "SELECT * FROM tb_m_profile WHERE lower(email) = lower(?1)", nativeQuery = true)
  Profile findByEmail(String email);

  @Override
  @Query(value = "SELECT * FROM tb_m_profile", nativeQuery = true)
  List<Profile> findAll();

}
