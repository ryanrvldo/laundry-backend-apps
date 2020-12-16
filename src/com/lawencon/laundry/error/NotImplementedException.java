package com.lawencon.laundry.error;

/**
 * @author Rian Rivaldo Rumapea
 */
@SuppressWarnings("serial")
public class NotImplementedException extends IllegalAccessException {

  public NotImplementedException() {
    super("Not implemented yet");
  }

}
