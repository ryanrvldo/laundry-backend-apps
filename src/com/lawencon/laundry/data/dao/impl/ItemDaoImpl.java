package com.lawencon.laundry.data.dao.impl;

import com.lawencon.laundry.data.dao.ItemDao;
import com.lawencon.laundry.data.entity.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ItemDaoImpl implements ItemDao {

	@Override
	public Item getEntity(Item request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT id FROM tb_m_item ",
				"WHERE lower(code) = lower(?) "
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, request.getCode());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Item(resultSet.getLong("id"));
		}
		return null;
	}

	@Override
	public Item addEntity(Item request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_item (code, type_name) ",
				"VALUES (?, ?) ",
				"RETURNING id"
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, request.getCode());
		statement.setString(2, request.getName());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Item(resultSet.getLong("id"));
		}
		return null;
	}

}
