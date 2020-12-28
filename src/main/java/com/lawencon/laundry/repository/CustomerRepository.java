package com.lawencon.laundry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lawencon.laundry.entity.Customer;

/**
 * @author Rian Rivaldo
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  String getCustomerNativeQuery = "SELECT c.id, c.profile_id, p.full_name, p.phone, p.email, p.address "
      + "FROM tb_m_customer c "
      + "INNER JOIN tb_m_profile p ON p.id = c.profile_id ";

  @Modifying
  @Override
  @Query(value = "DELETE FROM tb_m_customer WHERE id = ?1", nativeQuery = true)
  void deleteById(Long id);

  @Query(value = getCustomerNativeQuery, nativeQuery = true)
  List<Object[]> findAllWithProfile();

  @Query(value = getCustomerNativeQuery + "WHERE c.id = ?1", nativeQuery = true)
  Object findCustomerById(Long id);

  @Query(value = getCustomerNativeQuery + "WHERE p.id = ?1", nativeQuery = true)
  Object findByProfileId(Long id);

}
