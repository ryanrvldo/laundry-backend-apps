package com.lawencon.laundry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.entity.User;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  String getUserNativeQuery = "SELECT u.id, u.username, u.password, u.is_active, u.profile_id, " +
      "p.full_name, p.email, p.phone, p.address, u.role_id, " +
      "r.code, r.role_name " +
      "FROM tb_m_user AS u " +
      "INNER JOIN tb_m_profile AS p ON p.id = u.profile_id " +
      "INNER JOIN tb_m_role AS r ON r.id = u.role_id ";

  @Modifying
  @Query(value = "UPDATE tb_m_user SET is_active = ?1 WHERE id = ?2", nativeQuery = true)
  void updateActiveStatus(Boolean status, Long id);

  @Modifying
  @Query(value = "UPDATE tb_m_user SET password = ?1 WHERE id = ?2", nativeQuery = true)
  void updatePassword(String password, Long id);

  @Modifying
  @Override
  @Query(value = "DELETE FROM tb_m_user WHERE id = ?1", nativeQuery = true)
  void deleteById(Long id);

  @Query(value = getUserNativeQuery + "WHERE lower(u.username) = lower(?1)", nativeQuery = true)
  Object findByUsername(String username);

  @Query(value = getUserNativeQuery + "WHERE u.id = ?1", nativeQuery = true)
  Object findUserById(Long id);

  @Query(value = getUserNativeQuery, nativeQuery = true)
  List<Object[]> findAllWithProfile();
}
