package com.lawencon.laundry.data.dao;

import com.lawencon.laundry.config.DatabaseConnection;

import java.sql.Connection;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BaseDao<T> {

	Connection connection = DatabaseConnection.getConnection();

	T getEntity(T request) throws Exception;

	T addEntity(T request) throws Exception;

	default String buildSQLQueryOf(String... queries) {
		if (queries.length == 0) return "";

		StringBuilder builder = new StringBuilder();
		for (String query : queries) {
			builder.append(query);
		}
		return builder.toString();
	}

}
