package com.lawencon.laundry.util;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lawencon.laundry.error.DataAlreadyExistsException;
import com.lawencon.laundry.error.DataIsNotExistsException;
import com.lawencon.laundry.model.WebResponse;

/**
 * @author Rian Rivaldo
 */

public class WebResponseUtils {

  private static final Class<?>[] EXCEPTION_CLASSES = { NullPointerException.class, IllegalArgumentException.class,
      DataIsNotExistsException.class, DataAlreadyExistsException.class };

  public static <R> ResponseEntity<WebResponse<R>> createSuccessResponse(R result, HttpStatus status) {
	WebResponse<R> webResponse = new WebResponse<R>(result);
	return new ResponseEntity<>(webResponse, status);
  }

  public static <E extends Exception> ResponseEntity<WebResponse<String>> createErrorResponse(E e) {
	HttpStatus status = HttpStatus.BAD_REQUEST;
	String msg = "Something wrong with server.";
	Throwable cause = e.getCause();
	for (Class<?> eClass : EXCEPTION_CLASSES) {
	  if (cause != null && cause.getClass().equals(ConstraintViolationException.class)) {
		msg = "The data you want to change is used in other data.";
		break;
	  } else if (e.getClass().equals(eClass)) {
		msg = e.getLocalizedMessage();
	  } else {
		status = HttpStatus.INTERNAL_SERVER_ERROR;
	  }
	}
	e.printStackTrace();
	return new ResponseEntity<>(new WebResponse<>(msg), status);
  }

}
