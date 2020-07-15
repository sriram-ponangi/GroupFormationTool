package com.group14.app.services;

import java.util.List;

import com.group14.app.models.AllQuestions;

public interface ISurveyService {
	
	String getSurveyId(String courseId);

	List<AllQuestions> getAllSurveyQuestions(String surveyId);

	boolean isSurveyPublished(String surveyId);

	void publishSurvey(String surveyId);
	
}
