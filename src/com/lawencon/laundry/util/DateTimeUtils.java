package com.lawencon.laundry.util;

import java.time.format.DateTimeFormatter;

/**
 * @author Rian Rivaldo Rumapea
 */
public class DateTimeUtils {

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
	
}
