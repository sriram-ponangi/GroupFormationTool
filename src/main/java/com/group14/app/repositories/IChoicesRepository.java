package com.group14.app.repositories;

import java.sql.SQLException;

public interface IChoicesRepository {

	public boolean addQuestionMultiple(String id, String title, String text, String type, String displayText,
			String savedAs) throws SQLException;

	public boolean addQuestionSingle(String id, String title, String text, String type) throws SQLException;

}
