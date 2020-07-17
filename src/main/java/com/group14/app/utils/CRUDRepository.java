package com.group14.app.utils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface CRUDRepository<T> {
	<S extends T> boolean existsById(S entity) throws SQLException;

	List<HashMap<String, Object>> readData(T entity) throws SQLException;

	<S extends T> int save(S entity) throws SQLException;

	<S extends T> int[] saveTransaction(List<S> entities) throws SQLException;
// save can be used for delete as well

}