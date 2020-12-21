package com.lawencon.laundry.dao.hibernate.nativeImpl;

import com.lawencon.laundry.dao.ProfileDao;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.error.NotImplementedException;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class ProfileDaoImpl extends BaseDaoImpl<Profile> implements ProfileDao {

	public ProfileDaoImpl() {
		super(Profile.class);
	}

	@Override
	public Profile get(Profile data) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_m_profile ",
				"WHERE phone = ? AND lower(email) = lower(?) ");
		return getSingleResultWithQuery(sql, data.getPhone(), data.getEmail());
	}

	@Override
	public void insert(Profile data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_profile (full_name, phone, email, address) ",
				"VALUES (?1, ?2, ?3, ?4) ",
				"RETURNING id ");
		Long id = insertWithReturnId(sql,
				data.getFullName(),
				data.getPhone(),
				data.getEmail(),
				data.getAddress());
		data.setId(id);
	}

	@Override
	public void update(Profile data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(Profile data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<Profile> getAll() throws Exception {
		throw new NotImplementedException();
	}

}
