package com.lawencon.laundry.dao.hibernate.nativeImpl;

import com.lawencon.laundry.dao.ItemServiceDao;
import com.lawencon.laundry.entity.ItemService;
import com.lawencon.laundry.error.NotImplementedException;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class ItemServiceDaoImpl extends BaseDaoImpl<ItemService> implements ItemServiceDao {

	public ItemServiceDaoImpl() {
		super(ItemService.class);
	}

	@Override
	public ItemService get(ItemService data) throws Exception {
		String sql = buildQueryOf(
				"SELECT * FROM tb_m_laundry_service ",
				"WHERE lower(code) = lower(?) ");
		return getSingleResultWithQuery(sql, data.getCode());
	}

	@Override
	public void insert(ItemService data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_laundry_service (code, service_name, cost, hour_duration) ",
				"VALUES (?1, ?2, ?3, ?4) ");
		insertWithNoReturnQuery(sql,
				data.getCode(),
				data.getName(),
				data.getCost(),
				BigInteger.valueOf(data.getHourDuration()));
	}

	@Override
	public void update(ItemService data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(ItemService data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<ItemService> getAll() throws Exception {
		throw new NotImplementedException();
	}

}
