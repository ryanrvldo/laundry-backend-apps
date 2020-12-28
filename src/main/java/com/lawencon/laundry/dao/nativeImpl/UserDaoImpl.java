package com.lawencon.laundry.dao.nativeImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.UserDao;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.entity.User;

/**
 * @author Rian Rivaldo
 */
@Repository("user-nq")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

  public UserDaoImpl() {
	super(User.class);
  }

  private final String getUserNativeQuery = buildQueryOf(
      "SELECT u.id, u.username, u.password, u.is_active, u.profile_id, ",
      "p.full_name, p.email, p.phone, p.address, u.role_id, ",
      "r.code, r.role_name ",
      "FROM tb_m_user AS u ",
      "INNER JOIN tb_m_profile AS p ON p.id = u.profile_id ",
      "INNER JOIN tb_m_role AS r ON r.id = u.role_id ");

  @Override
  public void insert(User data) throws Exception {
	String sql = buildQueryOf(
	    "INSERT INTO tb_m_user (username, password, profile_id, role_id, is_active) ",
	    "VALUES (?1, ?2, ?3, ?4, ?5) ");
	executeUpdateWithQuery(sql,
	    data.getUsername(),
	    data.getPassword(),
	    BigInteger.valueOf(data.getProfile().getId()),
	    BigInteger.valueOf(data.getRole().getId()),
	    data.getIsActive());
  }

  @Override
  public void update(User data) throws Exception {
	String sql = buildQueryOf(
	    "UPDATE tb_m_user ",
	    "SET username = ?1, password = ?2, profile_id = ?3, role_id = ?4, is_active = ?5 ",
	    "WHERE id  = ?6 ");
	executeUpdateWithQuery(sql,
	    data.getUsername(), data.getPassword(), BigInteger.valueOf(data.getProfile().getId()),
	    BigInteger.valueOf(data.getRole().getId()), data.getIsActive());
  }

  @Override
  public void updateActiveStatus(User data) throws Exception {
	String sql = buildQueryOf(
	    "UPDATE tb_m_user SET is_active = ?1 ",
	    "WHERE id = ?2 ");
	executeUpdateWithQuery(sql, data.getIsActive(), data.getId());
  }

  @Override
  public void updatePassword(User data) throws Exception {
	String sql = buildQueryOf(
	    "UPDATE tb_m_user SET password = ?1 ",
	    "WHERE id = ?2 ");
	executeUpdateWithQuery(sql, data.getPassword(), data.getId());
  }

  @Override
  public void delete(User data) throws Exception {
	executeUpdateWithQuery("DELETE FROM tb_m_user WHERE id = ?1 ", data.getId());
  }

  @Override
  public User findByUsername(String username) throws Exception {
	String sql = buildQueryOf(getUserNativeQuery, "WHERE lower(u.username) = lower(?1)");
	Object obj = getEntityManager().createNativeQuery(sql)
	    .setParameter(1, username)
	    .getSingleResult();
	return setupUser((Object[]) obj);
  }

  @Override
  public User findById(Long id) throws Exception {
	String sql = buildQueryOf(getUserNativeQuery, "WHERE u.id = ?1");
	Object obj = getEntityManager().createNativeQuery(sql)
	    .setParameter(1, id)
	    .getSingleResult();
	return setupUser((Object[]) obj);
  }

  @Override
  public List<User> findAll() throws Exception {
	List<User> userList = new ArrayList<>();
	getAllWithQuery(getUserNativeQuery, objArr -> userList.add(setupUser(objArr)));
	return userList;
  }

  private User setupUser(Object[] objArr) {
	User user = new User();
	user.setId(Long.parseLong(objArr[0].toString()));
	user.setUsername((String) objArr[1]);
	user.setPassword((String) objArr[2]);
	user.setIsActive((Boolean) objArr[3]);

	Profile profile = new Profile();
	profile.setId(Long.parseLong(objArr[4].toString()));
	profile.setFullName((String) objArr[5]);
	profile.setEmail((String) objArr[6]);
	profile.setPhone((String) objArr[7]);
	profile.setAddress((String) objArr[8]);
	user.setProfile(profile);

	Role role = new Role();
	role.setId(Long.parseLong(objArr[9].toString()));
	role.setCode((String) objArr[10]);
	role.setName((String) objArr[11]);
	user.setRole(role);
	return user;
  }

}
