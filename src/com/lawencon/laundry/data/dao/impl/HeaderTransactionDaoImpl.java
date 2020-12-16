package com.lawencon.laundry.data.dao.impl;

import com.lawencon.laundry.data.dao.HeaderTransactionDao;
import com.lawencon.laundry.data.entity.HeaderTransaction;
import com.lawencon.laundry.error.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class HeaderTransactionDaoImpl implements HeaderTransactionDao {

	@Override
	public HeaderTransaction getEntity(HeaderTransaction request) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public HeaderTransaction addEntity(HeaderTransaction request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_r_hdr_transaction (receipt_number, customer_id, started_at, finished_at, total_cost, user_id, total_weight)",
				"VALUES (?, ?, ?, ?, ?, ?, ?) ",
				"RETURNING id"
		);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, request.getReceiptNumber());
		statement.setLong(2, request.getCustomer().getId());
		statement.setObject(3, request.getStartedAt());
		statement.setObject(4, request.getFinishedAt());
		statement.setBigDecimal(5, request.getTotalCost());
		statement.setLong(6, request.getUser().getId());
		statement.setDouble(7, request.getTotalWeight());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new HeaderTransaction(resultSet.getLong("id"));
		}
		return null;
	}

}
