package com.lawencon.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawencon.laundry.entity.ReturnTransaction;

/**
 * @author Rian Rivaldo
 */
public interface ReturnTransactionRepository extends JpaRepository<ReturnTransaction, Long> {
}
