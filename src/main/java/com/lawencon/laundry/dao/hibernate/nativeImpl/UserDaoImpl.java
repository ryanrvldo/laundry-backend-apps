package com.lawencon.laundry.dao.hibernate.nativeImpl;

import com.lawencon.laundry.dao.UserDao;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.entity.User;
import com.lawencon.laundry.error.NotImplementedException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public User get(User data) throws Exception {
		String sql = buildQueryOf(
				"SELECT u.id, u.username, u.user_password, u.profile_id, u.is_active, u.role_id, r.code, r.role_name ",
				"FROM tb_m_user AS u ",
				"INNER JOIN tb_m_role AS r ON r.id = u.role_id ",
				"WHERE lower(u.username) = lower(?1) AND u.user_password = ?2 ");
		return getSingleResultWithQuery(sql, data.getUsername(), data.getPassword());
	}

	@Override
	public void insert(User data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_user (username, user_password, profile_id, role_id) ",
				"VALUES (?1, ?2, ?3, ?4) ");
		insertWithNoReturnQuery(sql,
				data.getUsername(),
				data.getPassword(),
				BigInteger.valueOf(data.getProfile().getId()),
				BigInteger.valueOf(data.getRole().getId()));
	}

	@Override
	public void update(User data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(User data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<User> getAll() throws Exception {
		String sql = buildQueryOf(
				"SELECT u.id AS user_id, u.profile_id, u.role_id, r.code, r.role_name ",
				"FROM tb_m_user AS u ",
				"INNER JOIN tb_m_role AS r ON r.id = u.role_id ");
		List<User> userList = new ArrayList<>();
		getAllWithQuery(sql, objArr -> {
			User user = new User();
			user.setId(Long.parseLong(objArr[0].toString()));

			Profile profile = new Profile();
			profile.setId(Long.parseLong(objArr[1].toString()));
			user.setProfile(profile);

			Role role = new Role();
			role.setId(Long.parseLong(objArr[2].toString()));
			role.setCode((String) objArr[3]);
			role.setName((String) objArr[4]);
			user.setRole(role);

			userList.add(user);
		});
		return userList;
	}

}
