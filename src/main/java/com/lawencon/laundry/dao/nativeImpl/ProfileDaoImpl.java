package com.lawencon.laundry.dao.nativeImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.ProfileDao;
import com.lawencon.laundry.entity.Profile;

/**
 * @author Rian Rivaldo
 */
@Repository("profile-nq")
public class ProfileDaoImpl extends BaseDaoImpl<Profile> implements ProfileDao {

  public ProfileDaoImpl() {
	super(Profile.class);
  }

  @Override
  public void insert(Profile data) throws Exception {
	String sql = buildQueryOf(
	    "INSERT INTO tb_m_profile (full_name, email, phone, address) ",
	    "VALUES (?1, ?2, ?3, ?4) ",
	    "RETURNING id ");
	Long id = insertWithReturnId(sql,
	    data.getFullName(),
	    data.getEmail(),
	    data.getPhone(),
	    data.getAddress());
	data.setId(id);
  }

  @Override
  public void update(Profile data) throws Exception {
	String sql = buildQueryOf(
	    "UPDATE tb_m_profile ",
	    "SET full_name = ?1, email = ?2, phone = ?3, address = ?4 ",
	    "WHERE id = ?5");
	executeUpdateWithQuery(sql, data.getFullName(), data.getEmail(), data.getPhone(), data.getAddress(), data.getId());
  }

  @Override
  public void delete(Profile data) throws Exception {
	executeUpdateWithQuery("DELETE FROM tb_m_profile WHERE id = ?1", data.getId());
  }

  @Override
  public Profile findById(Long id) throws Exception {
	return getSingleResultWithQuery("SELECT * FROM tb_m_profile WHERE id = ?1", id);
  }

  @Override
  public Profile findByEmail(String email) throws Exception {
	return getSingleResultWithQuery("SELECT * FROM tb_m_profile WHERE lower(email) = lower(?1)", email);
  }

  @Override
  public List<Profile> findAll() throws Exception {
	List<Profile> profileList = new ArrayList<>();
	List<?> listObj = getEntityManager().createNativeQuery("SELECT * FROM tb_m_profile")
	    .getResultList();
	listObj.forEach(obj -> {
	  Profile profile = (Profile) obj;
	  profileList.add(profile);
	});
	return profileList;
  }

}
