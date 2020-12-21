package com.lawencon.laundry.util;

/**
 * @author Rian Rivaldo
 */
@FunctionalInterface
public interface ThrowableCallback {

	void run() throws Exception;

}
