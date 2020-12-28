package com.lawencon.laundry.util;

/**
 * @author Rian Rivaldo
 */
@FunctionalInterface
public interface ThrowableSupplier<T> {
	/**
	 * Gets a result.
	 *
	 * @return a result
	 */
	T get() throws Exception;

}
