package com.lawencon.laundry.dao.jdbc.basicImpl;

import com.lawencon.laundry.dao.HeaderTransactionDao;
import com.lawencon.laundry.entity.HeaderTransaction;
import com.lawencon.laundry.error.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class HeaderTransactionDaoImpl extends BaseDaoImpl implements HeaderTransactionDao {

	@Override
	public HeaderTransaction get(HeaderTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void insert(HeaderTransaction data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_r_hdr_transaction (receipt_number, customer_id, start_date, total_cost, total_weight, user_id)",
				"VALUES (?, ?, ?, ?, ?, ?) ",
				"RETURNING id");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setString(1, data.getReceiptNumber());
		statement.setLong(2, data.getCustomer().getId());
		statement.setObject(3, data.getStartDate());
		statement.setObject(4, data.getTotalCost());
		statement.setDouble(5, data.getTotalWeight());
		statement.setLong(6, data.getUser().getId());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			data.setId(rs.getLong("id"));
		}
	}

	@Override
	public void update(HeaderTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(HeaderTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<HeaderTransaction> getAll() throws Exception {
		throw new NotImplementedException();
	}

}
