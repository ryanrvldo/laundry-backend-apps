package com.lawencon.laundry.error;

/**
 * @author Rian Rivaldo
 */

public class DataAlreadyExistsException extends Exception {

  private static final long serialVersionUID = 2477142746167205713L;
  private static final String DEFAULT_MESSAGE = "Data already exists. ";

  public DataAlreadyExistsException() {
  }

  public DataAlreadyExistsException(String param) {
	super(DEFAULT_MESSAGE + "With parameter: " + param);
  }

  public DataAlreadyExistsException(Long id) {
	super(DEFAULT_MESSAGE + "With id : " + id);
  }

}
