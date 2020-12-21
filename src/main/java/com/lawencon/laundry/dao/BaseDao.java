package com.lawencon.laundry.dao;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public interface BaseDao<T> {

	T get(T data) throws Exception;

	void insert(T data) throws Exception;

	void update(T data) throws Exception;

	void delete(T data) throws Exception;

	List<T> getAll() throws Exception;

	default String buildQueryOf(String... queries) {
		int queryLength = queries.length;
		if (queryLength == 0) {
			return "";
		} else if (queryLength == 1) {
			return queries[0];
		}

		StringBuilder builder = new StringBuilder();
		for (String query : queries) {
			builder.append(query);
		}
		return builder.toString();
	}

}
