package com.lawencon.laundry.dao.nativeImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.RoleDao;
import com.lawencon.laundry.entity.Role;

/**
 * @author Rian Rivaldo
 */
@Repository("role-nq")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

  public RoleDaoImpl() {
	super(Role.class);
  }

  @Override
  public Role findById(Long id) throws Exception {
	return getSingleResultWithQuery("SELECT * FROM tb_m_role WHERE id = ?1 ", id);
  }

  @Override
  public Role findByCode(String code) throws Exception {
	return getSingleResultWithQuery("SELECT * FROM tb_m_role WHERE lower(code) = lower(?1) ", code);
  }

  @Override
  public void insert(Role data) throws Exception {
	String sql = buildQueryOf(
	    "INSERT INTO tb_m_role (code, role_name) ",
	    "VALUES (?1, ?2) ",
	    "RETURNING id");
	Long id = insertWithReturnId(sql, data.getCode(), data.getName());
	data.setId(id);
  }

  @Override
  public void update(Role data) throws Exception {
	String sql = buildQueryOf(
	    "UPDATE tb_m_role ",
	    "SET code = ?1, role_name = ?2 ",
	    "WHERE id = ?3 ");
	executeUpdateWithQuery(sql,
	    data.getCode(),
	    data.getName(),
	    data.getId());
  }

  @Override
  public void delete(Role data) throws Exception {
	executeUpdateWithQuery("DELETE FROM tb_m_role WHERE id = ?1 ", data.getId());
  }

  @Override
  public List<Role> findAll() throws Exception {
	String sql = "SELECT id, code, role_name FROM tb_m_role ";
	List<Role> results = new ArrayList<>();
	getAllWithQuery(sql, objArr -> {
	  Role role = new Role(Long.parseLong(objArr[0].toString()), (String) objArr[1], (String) objArr[2]);
	  results.add(role);
	});
	return results;
  }

}
