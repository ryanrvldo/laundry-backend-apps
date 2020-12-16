package com.lawencon.laundry.util;

/**
 * @author Rian Rivaldo Rumapea
 */
@FunctionalInterface
public interface ThrowableSupplier<T> {

	T get() throws Exception;

}
