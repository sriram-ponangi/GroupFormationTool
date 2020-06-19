package com.group14.app.utils;

import java.util.HashMap;
import java.util.List;

public interface CRUDRepository<T> {
	<S extends T> boolean existsById(S entity);

	List<HashMap<String, Object>> readData(T entity);

	<S extends T> int save(S entity);

	<S extends T> int[] saveTransaction(List<S> entities);
// save can be used for delete as well

}