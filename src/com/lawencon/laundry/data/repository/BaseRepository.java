package com.lawencon.laundry.data.repository;

import com.lawencon.laundry.util.ThrowableSupplier;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BaseRepository<T> {

	T get(T item) throws Exception;

	T add(T item) throws Exception;

	default T checkBeforeAdd(T item, ThrowableSupplier<T> supplier) throws Exception {
		T checked = get(item);
		if (checked == null) {
			return supplier.get();
		}
		return checked;
	}

}
