package com.lawencon.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawencon.laundry.entity.Member;

/**
 * @author Rian Rivaldo
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
