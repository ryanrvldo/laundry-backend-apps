package com.lawencon.laundry.dao;

import com.lawencon.laundry.entity.Role;

/**
 * @author Rian Rivaldo
 */
public interface RoleDao extends BaseDao<Role> {

  Role findById(Long id) throws Exception;

  Role findByCode(String code) throws Exception;

}
