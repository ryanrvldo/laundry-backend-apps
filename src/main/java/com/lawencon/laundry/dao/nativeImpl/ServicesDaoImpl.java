package com.lawencon.laundry.dao.nativeImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.ServicesDao;
import com.lawencon.laundry.entity.Services;

/**
 * @author Rian Rivaldo
 */
@Repository("service-nq")
public class ServicesDaoImpl extends BaseDaoImpl<Services> implements ServicesDao {

  public ServicesDaoImpl() {
	super(Services.class);
  }

  @Override
  public void insert(Services data) throws Exception {
	String sql = buildQueryOf(
	    "INSERT INTO tb_m_service (code, service_name, hour_duration) ",
	    "VALUES (?1, ?2, ?3) RETURNING id");
	Long id = insertWithReturnId(sql, data.getCode(), data.getName(), data.getHourDuration());
	data.setId(id);
  }

  @Override
  public void update(Services data) throws Exception {
	String sql = buildQueryOf(
	    "UPDATE tb_m_service ",
	    "SET code = ?1, service_name = ?2, hour_duration = ?3 ",
	    "WHERE id = ?4 ");
	executeUpdateWithQuery(sql, data.getCode(), data.getName(), data.getHourDuration(),
	    data.getId());
  }

  @Override
  public void delete(Services data) throws Exception {
	executeUpdateWithQuery("DELETE FROM tb_m_service WHERE id = ?1", data.getId());
  }

  @Override
  public List<Services> findAll() throws Exception {
	String sql = "SELECT id, code, service_name, hour_duration FROM tb_m_service";
	List<Services> results = new ArrayList<>();
	getAllWithQuery(sql, objArr -> {
	  Services item = new Services(Long.parseLong(objArr[0].toString()), (String) objArr[1], (String) objArr[2],
	      Long.parseLong(objArr[3].toString()));
	  results.add(item);
	});
	return results;
  }

  @Override
  public Services findById(Long id) throws Exception {
	return getSingleResultWithQuery("SELECT * FROM tb_m_service WHERE id = ?1", id);
  }

  @Override
  public Services findByCode(String code) throws Exception {
	return getSingleResultWithQuery("SELECT * FROM tb_m_service WHERE lower(code) = ?1", code);
  }

}
