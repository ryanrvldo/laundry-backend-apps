package com.lawencon.laundry.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.RoleDao;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.error.DataIsNotExistsException;
import com.lawencon.laundry.service.RoleService;
import com.lawencon.laundry.util.ValidationUtils;

/**
 * @author Rian Rivaldo
 */
@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  @Qualifier("role-nq")
  private RoleDao roleDao;

  @Autowired
  private ValidationUtils validationUtils;

  @Override
  public void createRole(Role role) throws Exception {
	Objects.requireNonNull(role, () -> "Role data must be submitted.");
	validationUtils.validateString(role.getCode(), role.getName());
	roleDao.insert(role);
  }

  @Override
  public Role getRoleById(Long id) throws Exception {
	Objects.requireNonNull(id, () -> "Role id must be submitted.");
	validationUtils.validateEntityId(id, null);
	return Optional.ofNullable(validationUtils.validateGet(() -> roleDao.findById(id)))
	    .orElseThrow(() -> new DataIsNotExistsException(id));
  }

  @Override
  public Role getRoleByCode(String code) throws Exception {
	Objects.requireNonNull(code, () -> "Role code must be submitted.");
	return Optional.ofNullable(validationUtils.validateGet(() -> roleDao.findByCode(code)))
	    .orElseThrow(() -> new DataIsNotExistsException(code));
  }

  @Override
  public List<Role> getAllRole() throws Exception {
	List<Role> roleList = roleDao.findAll();
	if (roleList.isEmpty()) {
	  throw new NullPointerException("Roles data is empty.");
	}
	return roleList;
  }

  @Transactional
  @Override
  public void updateRole(Role role) throws Exception {
	Long id = Optional.ofNullable(role)
	    .map(r -> r.getId())
	    .orElseThrow(() -> new NullPointerException("Role id must be submitted"));
	validationUtils.validateEntityId(id, () -> getRoleById(id));
	validationUtils.validateString(role.getCode(), role.getName());
	roleDao.update(role);
  }

  @Transactional
  @Override
  public void deleteRoleByCode(String code) throws Exception {
	Objects.requireNonNull(code, () -> "Role code must be submitted.");
	validationUtils.validateString(code);
	Role role = new Role();
	role.setCode(code);
	roleDao.delete(role);
  }

}
