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
	public void deleteQuestions(int id) throws SQLException {

		String SQL_DELETE_ALL_QUESTION = "delete from AllQuestions where question_id=?";
		String SQL_DELETE_OPTIONS = "delete from MultipleChoiceQuestions where question_id =?";
		List<Object> params = new ArrayList<>();
		params.add(id);

		int deleteOptions = db.save(new SQLInput(SQL_DELETE_OPTIONS, params));
		int deleteAllQuestion = db.save(new SQLInput(SQL_DELETE_ALL_QUESTION, params));

	}
	
	@Override
	public void deleteAnswers(int id) throws SQLException {

		String SQL_DELETE_NUMERIC_RESPONSES = "delete from NumericResponses where response_id = (select response_id from SurveyQuestionsMapper where question_id =?)";
		String SQL_DELETE_TEXT_RESPONSES = "delete from TextResponses where response_id = (select response_id from SurveyQuestionsMapper where question_id =?)";
		List<Object> params = new ArrayList<>();
		params.add(id);

		int deleteNumericResponses = db.save(new SQLInput(SQL_DELETE_NUMERIC_RESPONSES, params));
		int deleteTextReponses = db.save(new SQLInput(SQL_DELETE_TEXT_RESPONSES, params));
		System.out.println(deleteNumericResponses);
		System.out.println(deleteTextReponses);
	}

	@Override
	public void deleteMapping(int id) throws SQLException {

		String SQL_ANSWER_MAPPING = "delete from SurveyQuestionsMapper where question_id =?";
		List<Object> params = new ArrayList<>();
		params.add(id);

		int deleteMapping = db.save(new SQLInput(SQL_ANSWER_MAPPING, params));

	}
	
//	public void deleteQuestionAsTransaction(int id)
//	{
//		List<SQLInput> list = new ArrayList<>();
//		List<Object> params = new ArrayList<>();
//		params.add(id);
//		
//		String SQL_ANSWER_MAPPING = "delete from SurveyQuestionsMapper where question_id =?";
//		list.add(new SQLInput(SQL_ANSWER_MAPPING, params));
//		
//		db.saveTransaction(list);
//	}
}
