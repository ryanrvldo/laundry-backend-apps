package com.lawencon.laundry.dao.jdbc.basicImpl;

import com.lawencon.laundry.dao.ItemDao;
import com.lawencon.laundry.entity.Item;
import com.lawencon.laundry.error.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class ItemDaoImpl extends BaseDaoImpl implements ItemDao {

	@Override
	public Item get(Item data) throws Exception {
		String sql = buildQueryOf(
				"SELECT id FROM tb_m_item ",
				"WHERE lower(code) = lower(?) ");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setString(1, data.getCode());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			Item item = new Item();
			item.setId(rs.getLong("id"));
			return item;
		}
		return null;
	}

	@Override
	public void insert(Item data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_m_item (code, type_name) ",
				"VALUES (?, ?) ",
				"RETURNING id");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setString(1, data.getCode());
		statement.setString(2, data.getName());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			data.setId(rs.getLong("id"));
		}
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
