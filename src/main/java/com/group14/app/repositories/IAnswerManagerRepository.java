package com.group14.app.repositories;

import java.sql.SQLException;

public interface IAnswerManagerRepository {

	public void deleteQuestionAsTransaction(int id) throws SQLException;
}
