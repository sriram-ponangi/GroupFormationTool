package com.group14.app.models;

import java.util.List;

public class SQLInput {
	private String sql;
	private List<Object> parameters;

	public SQLInput(String sql, List<Object> parameters) {
		super();
		this.sql = sql;
		this.parameters = parameters;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "SQLInput [sql=" + sql + ", parameters=" + parameters + "]\n";
	}

}
