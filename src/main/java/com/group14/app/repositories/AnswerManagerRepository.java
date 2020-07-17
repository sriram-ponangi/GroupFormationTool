package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

@Repository
public class AnswerManagerRepository implements IAnswerManagerRepository {

	private CRUDRepository<SQLInput> db;

	public AnswerManagerRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	@Override
	public void deleteQuestionAsTransaction(int id) throws SQLException {
		List<SQLInput> list = new ArrayList<>();
		List<Object> params = new ArrayList<>();
		params.add(id);

		String SQL_DELETE_ALL_QUESTION = "delete from AllQuestions where question_id=?";
		String SQL_DELETE_OPTIONS = "delete from MultipleChoiceQuestions where question_id =?";
		String SQL_DELETE_NUMERIC_RESPONSES = "delete from NumericResponses where response_id = (select response_id from SurveyQuestionsMapper where question_id =?)";
		String SQL_DELETE_TEXT_RESPONSES = "delete from TextResponses where response_id = (select response_id from SurveyQuestionsMapper where question_id =?)";
		String SQL_ANSWER_MAPPING = "delete from SurveyQuestionsMapper where question_id =?";

		list.add(new SQLInput(SQL_DELETE_NUMERIC_RESPONSES, params));
		list.add(new SQLInput(SQL_DELETE_TEXT_RESPONSES, params));
		list.add(new SQLInput(SQL_ANSWER_MAPPING, params));
		list.add(new SQLInput(SQL_DELETE_OPTIONS, params));
		list.add(new SQLInput(SQL_DELETE_ALL_QUESTION, params));

		db.saveTransaction(list);
	}
}
