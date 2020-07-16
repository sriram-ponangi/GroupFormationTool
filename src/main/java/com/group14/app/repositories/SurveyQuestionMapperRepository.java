package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.group14.app.models.SQLInput;
import com.group14.app.models.SurveyQuestionMapper;
import com.group14.app.utils.CRUDRepository;

@Repository
public class SurveyQuestionMapperRepository implements ISurveyQuestionMapperRepository {
	
	private CRUDRepository<SQLInput> db;

	public SurveyQuestionMapperRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	@Override
	public ArrayList<SurveyQuestionMapper> getAllSurveyQuestions(int surveyId) throws SQLException {
		
		String sqlQuery = "SELECT * FROM SurveyQuestionsMapper WHERE survey_id = ?";
		List<Object> params = new ArrayList<>();
		params.add(surveyId);

		final ArrayList<SurveyQuestionMapper> rows = new ArrayList<SurveyQuestionMapper>();

		List<HashMap<String, Object>> questionsData = db.readData(new SQLInput(sqlQuery, params));

		if (questionsData != null)
			questionsData.stream().forEach(row -> {
				SurveyQuestionMapper question = new SurveyQuestionMapper();
				question.setSurveyId((int) row.get("survey_id"));
				question.setQuestionId((int) row.get("question_id"));
				question.setResponseId((int) row.get("response_id"));
				rows.add(question);
			});
		else {
			System.out.println("Could not Execute: " + sqlQuery);
			return null;
		}
		return rows;
	}

	@Override
	public int deleteSurveyQuestion(int surveyId, int questionId) throws SQLException {

		String sqlQuery = "SET FOREIGN_KEY_CHECKS=0;DELETE FROM SurveyQuestionsMapper WHERE question_id = ? AND survey_id = ?";

		List<Object> params = new ArrayList<>();
		params.add(surveyId);
		params.add(questionId);

		int rowsUpdated = db.save(new SQLInput(sqlQuery, params));

		return rowsUpdated;

	}

	@Override
	public int addSurveyQuestion(int surveyId, int questionId) throws SQLException {
		String sqlQuery = "INSERT INTO SurveyQuestionsMapper ( survey_id, question_id ) VALUES (?, ?)";
		List<Object> params = new ArrayList<>();
		params.add(surveyId);
		params.add(questionId);

		int rowsUpdated = db.save(new SQLInput(sqlQuery, params));

		if (rowsUpdated == 0) {
			System.out.println("Could not Execute: " + sqlQuery);
			return 0;
		}
		else {
			return rowsUpdated;
		}
	}
		
}
