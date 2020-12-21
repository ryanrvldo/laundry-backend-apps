package com.lawencon.laundry.dao.hibernate.nativeImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Rian Rivaldo
 */
class BaseDaoImpl<T> {

	private final Class<T> resultClass;
	private SessionFactory sessionFactory;

	public BaseDaoImpl(Class<T> resultClass) {
		this.resultClass = resultClass;
	}

	protected T getSingleResultWithQuery(String query, Object... params) {
		NativeQuery<T> nativeQuery = getNativeQueryWithInputtedParams(query, params);
		return nativeQuery.getSingleResult();
	}

	protected Long insertWithReturnId(String query, Object... params) {
		NativeQuery<?> nativeQuery = getSession().createNativeQuery(query);
		setNativeQueryParams(nativeQuery, params);
		List<?> result = nativeQuery
				.getResultList();
		return (result.size() > 0 ? Long.parseLong(result.get(0).toString()) : null);
	}

	protected void insertWithNoReturnQuery(String query, Object... params) throws Exception {
		NativeQuery<T> nativeQuery = getNativeQueryWithInputtedParams(query, params);
		int rowUpdated = nativeQuery.executeUpdate();
		if (rowUpdated == 0) {
			throw new Exception("Failed to insert data.");
		}
	}

	protected void getAllWithQuery(String query, Consumer<Object[]> consumer) {
		List<?> objList = getSession()
				.createNativeQuery(query)
				.getResultList();
		objList.forEach(val -> {
			Object[] objArr = (Object[]) val;
			consumer.accept(objArr);
		});
	}

	private NativeQuery<T> getNativeQueryWithInputtedParams(String query, Object... params) {
		NativeQuery<T> nativeQuery = getSession().createNativeQuery(query, resultClass);
		setNativeQueryParams(nativeQuery, params);
		return nativeQuery;
	}

	private void setNativeQueryParams(NativeQuery<?> nativeQuery, Object... params) {
		int paramLength = params.length;
		if (paramLength == 0) return;
		for (int i = 0; i < paramLength; i++) {
			nativeQuery.setParameter(i + 1, params[i]);
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

}
