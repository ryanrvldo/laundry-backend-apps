package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.entity.Role;

/**
 * @author Rian Rivaldo
 */

public interface RoleService {
  void createRole(Role role) throws Exception;

  Role getRoleById(Long id) throws Exception;

  Role getRoleByCode(String code) throws Exception;

  List<Role> getAllRole() throws Exception;

  void updateRole(Role role) throws Exception;

  void deleteRoleByCode(String code) throws Exception;
}
