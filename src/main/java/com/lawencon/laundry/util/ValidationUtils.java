package com.lawencon.laundry.util;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Component;

import com.lawencon.laundry.error.DataIsNotExistsException;

/**
 * @author Rian Rivaldo
 */
@Component
public class ValidationUtils {

  public void validateString(String... params) throws Exception {
	for (String param : params) {
	  if (Objects.isNull(param) || param.isEmpty() || param.trim().equals("")) {
		throw new IllegalArgumentException("All the input must be filled.");
	  }
	}
  }

  public <T> T validateEntityId(Long id, ThrowableSupplier<T> supplier) throws Exception {
	Objects.requireNonNull(id, () -> "Entity id must be submitted.");
	if (id <= 0) {
	  throw new IllegalArgumentException("Invalid id: " + id);
	}
	if (supplier == null) {
	  return null;
	}
	return Optional.ofNullable(validateGet(supplier))
	    .orElseThrow(() -> new DataIsNotExistsException(id));
  }

  public <T> T validateGet(ThrowableSupplier<T> supplier) throws Exception {
	try {
	  return supplier.get();
	} catch (NullPointerException | NoResultException e) {
	  return null;
	}
  }

}
