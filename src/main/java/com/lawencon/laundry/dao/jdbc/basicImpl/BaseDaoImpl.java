package com.lawencon.laundry.dao.jdbc.basicImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Rian Rivaldo
 */
class BaseDaoImpl {

	private Connection connection;

	public void setDataSource(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected Connection getConnection() {
		return this.connection;
	}

}
