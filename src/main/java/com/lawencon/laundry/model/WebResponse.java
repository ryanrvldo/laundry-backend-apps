package com.lawencon.laundry.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo
 */
@JsonInclude(value = Include.NON_NULL)
public class WebResponse<R> {

  private R result;

  public WebResponse(R result) {
	this.result = result;
  }

  public R getResult() {
	return result;
  }

  public void setResult(R result) {
	this.result = result;
  }

}
