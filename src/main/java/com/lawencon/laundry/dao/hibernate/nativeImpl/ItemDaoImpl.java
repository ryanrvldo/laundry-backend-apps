package com.lawencon.laundry.dao.hibernate.nativeImpl;

import com.lawencon.laundry.dao.ItemDao;
import com.lawencon.laundry.entity.Item;
import com.lawencon.laundry.error.NotImplementedException;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class ItemDaoImpl extends BaseDaoImpl<Item> implements ItemDao {

	public ItemDaoImpl() {
		super(Item.class);
	}

	@Override
	public Item get(Item data) throws Exception {
		String sql = buildQueryOf(
				"SELECT * ",
				"FROM tb_m_item AS i ",
				"WHERE lower(code) = lower(?1) AND service_id = ?2 ");
		return getSingleResultWithQuery(sql,
				data.getCode(),
				BigInteger.valueOf(data.getItemService().getId()));
	}

	@Override
	public void insert(Item data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_item (code, item_name, service_id) ",
				"VALUES (?1, ?2, ?3) ");
		insertWithNoReturnQuery(sql,
				data.getCode(),
				data.getName(),
				BigInteger.valueOf(data.getItemService().getId()));
	}

	@Override
	public void update(Item data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(Item data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<Item> getAll() throws Exception {
		throw new NotImplementedException();
	}

}
