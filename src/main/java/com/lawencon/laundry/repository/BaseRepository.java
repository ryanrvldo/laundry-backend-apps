package com.lawencon.laundry.repository;

import com.lawencon.laundry.util.ThrowableCallback;

import javax.persistence.NoResultException;

/**
 * @author Rian Rivaldo
 */
public interface BaseRepository<T> {

	T get(T data) throws Exception;

	void add(T data) throws Exception;

	default void checkBeforeAdd(T data, ThrowableCallback callback) throws Exception {
		try {
			data = get(data);
		} catch (Exception e) {
			if (e instanceof NoResultException) {
				data = null;
			} else {
				throw new Exception(e);
			}
		}
		if (data == null) {
			callback.run();
		}
	}

}
