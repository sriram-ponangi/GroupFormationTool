package com.group14.app.services;

import java.sql.SQLException;
import java.util.List;

import com.group14.app.models.AllQuestions;
import com.group14.app.models.Survey;

public interface ISurveyService {

	int getSurveyId(String courseId) throws SQLException;

	List<AllQuestions> getAllSurveyQuestions(String surveyId);

	boolean isSurveyPublished(String surveyId);

	void publishSurvey(String surveyId);

	int createSurvey(Survey survey) throws SQLException;

}
