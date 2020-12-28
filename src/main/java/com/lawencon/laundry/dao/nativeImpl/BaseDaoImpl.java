package com.lawencon.laundry.dao.nativeImpl;

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lawencon.laundry.error.DataIsNotExistsException;

/**
 * @author Rian Rivaldo
 */
class BaseDaoImpl<T> {

  private final Class<T> resultClass;

  @PersistenceContext
  private EntityManager entityManager;

  public BaseDaoImpl(Class<T> resultClass) {
	this.resultClass = resultClass;
  }

  protected T getSingleResultWithQuery(String queryStr, Object... params) {
	Query queryObj = createQueryWithInputtedParams(queryStr, params);
	return resultClass.cast(queryObj.getSingleResult());
  }

  protected Long insertWithReturnId(String queryStr, Object... params) {
	Query queryObj = getEntityManager().createNativeQuery(queryStr);
	setNativeQueryParams(queryObj, params);
	Object result = queryObj.getSingleResult();
	return Long.parseLong(result.toString());
  }

  protected void executeUpdateWithQuery(String query, Object... params) throws Exception {
	Query queryObj = createQueryWithInputtedParams(query, params);
	int row = queryObj.executeUpdate();
	if (row == 0) {
	  throw new DataIsNotExistsException();
	}
  }

  protected void getAllWithQuery(String query, Consumer<Object[]> consumer) {
	List<?> objList = getEntityManager()
	    .createNativeQuery(query)
	    .getResultList();
	objList.forEach(val -> {
	  Object[] objArr = (Object[]) val;
	  consumer.accept(objArr);
	});
  }

  private Query createQueryWithInputtedParams(String query, Object... params) {
	Query queryObj = getEntityManager().createNativeQuery(query, resultClass);
	setNativeQueryParams(queryObj, params);
	return queryObj;
  }

  private void setNativeQueryParams(Query query, Object... params) {
	int paramLength = params.length;
	if (paramLength == 0) return;
	for (int i = 0; i < paramLength; i++) {
	  query.setParameter(i + 1, params[i]);
	}
  }

  protected EntityManager getEntityManager() {
	return this.entityManager;
  }

}
