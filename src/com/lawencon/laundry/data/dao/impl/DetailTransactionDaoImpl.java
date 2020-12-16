package com.lawencon.laundry.data.dao.impl;

import com.lawencon.laundry.data.dao.DetailTransactionDao;
import com.lawencon.laundry.data.entity.DetailTransaction;
import com.lawencon.laundry.error.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class DetailTransactionDaoImpl implements DetailTransactionDao {

	@Override
	public DetailTransaction getEntity(DetailTransaction request) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public DetailTransaction addEntity(DetailTransaction request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_r_dtl_transaction (hdr_id, item_id, quantity, item_cost, laundry_type_id) ",
				"VALUES (?, ?, ?, ?, ?) ",
				"RETURNING id "
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, request.getHeaderTransaction().getId());
		statement.setLong(2, request.getItem().getId());
		statement.setInt(3, request.getQuantity());
		statement.setBigDecimal(4, request.getItemCost());
		statement.setLong(5, request.getLaundryType().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new DetailTransaction(resultSet.getLong("id"));
		}
		return null;
	}

}
