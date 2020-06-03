package com.group14.app.utils;


import java.util.Map;
import java.util.List;


interface CRUDRepository<T> {
	<S extends T> boolean existsById(S entity);	
	List<? extends Map<String,Object>> readData(T entity);
    <S extends T> int save(S entity);
    <S extends T> int[] saveTransaction(List<S> entities);
// save can be used for delete as well
//	<S extends I> void delete(S entity, Function f);
//	<S extends I> void deleteTransaction(List<S> entities);
    
}