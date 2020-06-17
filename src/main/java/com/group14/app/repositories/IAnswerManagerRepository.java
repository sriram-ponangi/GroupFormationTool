package com.group14.app.repositories;

import java.sql.SQLException;

public interface IAnswerManagerRepository {

	public void deleteQuestions(int id) throws SQLException;

	public void deleteAnswers(int id) throws SQLException;

	public void deleteMapping(int id) throws SQLException;
}
