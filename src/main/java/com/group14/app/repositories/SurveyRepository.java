package com.group14.app.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.group14.app.models.SQLInput;
import com.group14.app.models.Survey;
import com.group14.app.models.SurveyQuestionMapper;
import com.group14.app.utils.CRUDRepository;

@Repository
public class SurveyRepository implements ISurveyRepository {

	private CRUDRepository<SQLInput> db;

	private static final Logger LOG = LoggerFactory.getLogger(SurveyRepository.class);	

	public SurveyRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	@Override
	public Survey getSurveyInfo(String courseId) {
		LOG.info("Getting Survey Info from DB for {} courseId",courseId);
		final String SQL = " SELECT * FROM Surveys WHERE course_id = ?";
		final List<Object> params = new ArrayList<>();
		params.add(courseId);
		final SQLInput sqlInput = new SQLInput(SQL, params);

		final List<HashMap<String, Object>> surveyInfo = db.readData(sqlInput);

		final Survey survey = new Survey();

		if (surveyInfo != null) {
			surveyInfo.stream().forEach(row -> {
				survey.setSurveyId((Integer) row.get("survey_id"));
				survey.setCourseId((String) row.get("course_id"));
				survey.setPublished((Integer) row.get("published"));
				survey.setGroupSize((Integer) row.get("group_size"));
			});
		} else {
			LOG.error("Could not Execute: " + SQL);
			return null;
		}
		return survey;
	}

	@Override
	public List<SurveyQuestionMapper> getSurveyQuestionsInfo(int surveyId) {

		final String SQL = " SELECT * FROM SurveyQuestionsMapper WHERE survey_id = ?";
		final List<Object> params = new ArrayList<>();
		params.add(surveyId);
		final SQLInput sqlInput = new SQLInput(SQL, params);

		final List<HashMap<String, Object>> surveyQuestionsInfo = db.readData(sqlInput);

		final List<SurveyQuestionMapper> surveyQuestions = new ArrayList<>();

		if (surveyQuestionsInfo != null) {
			surveyQuestionsInfo.stream().forEach(row -> {
				SurveyQuestionMapper sqm = new SurveyQuestionMapper();
				sqm.setQuestionId((Integer) row.get("question_id"));
				sqm.setResponseId((Integer) row.get("response_id"));
				sqm.setSurveyId((Integer) row.get("survey_id"));

				surveyQuestions.add(sqm);

			});
		} else {
			LOG.error("Could not Execute: " + SQL);
			return null;
		}
		return surveyQuestions;
	}

	@Override
	public void publishSurvey(int surveyId) {
		final String SQL = "UPDATE Surveys SET published = 1 WHERE survey_id = ?";
		final List<Object> params = new ArrayList<>();
		params.add(surveyId);
		final SQLInput sqlInput = new SQLInput(SQL, params);
		db.save(sqlInput);
	}
	
	@Override
	public void unpublishSurvey(int surveyId) {
		final String SQL = "UPDATE Surveys SET published = 0 WHERE survey_id = ?";
		final List<Object> params = new ArrayList<>();
		params.add(surveyId);
		final SQLInput sqlInput = new SQLInput(SQL, params);
		db.save(sqlInput);
	}

}
