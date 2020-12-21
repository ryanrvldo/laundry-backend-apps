package com.lawencon.laundry.dao.jdbc.basicImpl;

import com.lawencon.laundry.dao.DetailTransactionDao;
import com.lawencon.laundry.entity.DetailTransaction;
import com.lawencon.laundry.entity.HeaderTransaction;
import com.lawencon.laundry.error.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class DetailTransactionDaoImpl extends BaseDaoImpl implements DetailTransactionDao {

	@Override
	public DetailTransaction get(DetailTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void insert(DetailTransaction data) throws Exception {
		String sql = buildQueryOf(
				"INSERT INTO tb_r_dtl_transaction (hdr_id, item_id, quantity, finish_date) ",
				"VALUES (?, ?, ?, ?) ",
				"RETURNING id ");
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setLong(1, data.getHeaderTransaction().getId());
		statement.setLong(2, data.getItem().getId());
		statement.setInt(3, data.getQuantity());
		statement.setObject(4, data.getFinishDate());

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			data.setId(rs.getLong("id"));
		}
	}

	@Override
	public void update(DetailTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void delete(DetailTransaction data) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<DetailTransaction> getAll() throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List<DetailTransaction> getAllByHeaderId(HeaderTransaction header) throws Exception {
		throw new NotImplementedException();
	}

}
