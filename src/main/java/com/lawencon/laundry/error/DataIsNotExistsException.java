package com.lawencon.laundry.error;

/**
 * @author Rian Rivaldo
 */

public class DataIsNotExistsException extends Exception {

  private static final long serialVersionUID = -6767399699885481998L;
  private final static String DEFAULT_MESSAGE = "Data is not exists. ";

  public DataIsNotExistsException() {
	super(DEFAULT_MESSAGE);
  }

  public DataIsNotExistsException(Long id) {
	super(DEFAULT_MESSAGE + "With id : " + id);
  }

  public DataIsNotExistsException(String param) {
	super(DEFAULT_MESSAGE + "With parameter: " + param);
  }

}
