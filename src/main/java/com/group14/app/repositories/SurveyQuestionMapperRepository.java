package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.group14.app.controllers.SurveyController;
import com.group14.app.models.SQLInput;
import com.group14.app.models.SurveyQuestionMapper;
import com.group14.app.utils.CRUDRepository;

@Repository
public class SurveyQuestionMapperRepository implements ISurveyQuestionMapperRepository {

	private CRUDRepository<SQLInput> db;
	private static final Logger LOG = LoggerFactory.getLogger(SurveyQuestionMapperRepository.class);

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
	public int[] deleteSurveyQuestion(int responseId) throws SQLException {

		final String SURVEY_RULE_MAPPER_DELETE = "DELETE FROM SurveyRuleMapper WHERE response_id = ?";
		final String NUMERIC_RESPONSES_DELETE = "DELETE FROM NumericResponses WHERE response_id = ?";
		final String TEXT_RESPONSES_DELETE = "DELETE FROM TextResponses WHERE response_id = ?";
		final String SURVEY_QUESTION_MAPPER_DELETE = "DELETE FROM SurveyQuestionsMapper WHERE response_id = ?";

		final List<SQLInput> transactionsQueries = new ArrayList<>();
		final List<Object> transactionParams = new ArrayList<>();
		transactionParams.add(responseId);
		LOG.debug("Deleting rows from {}, {}, {}, {} these tables with responseId: {}", "SurveyQuestionMapper",
				"NumericResponses", "TextResponses", "SurveyRuleMapper", responseId);

		transactionsQueries.add(new SQLInput(SURVEY_RULE_MAPPER_DELETE, transactionParams));
		transactionsQueries.add(new SQLInput(NUMERIC_RESPONSES_DELETE, transactionParams));
		transactionsQueries.add(new SQLInput(TEXT_RESPONSES_DELETE, transactionParams));
		transactionsQueries.add(new SQLInput(SURVEY_QUESTION_MAPPER_DELETE, transactionParams));

		int[] rowsUpdated = db.saveTransaction(transactionsQueries);

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
		} else {
			return rowsUpdated;
		}
	}

}
