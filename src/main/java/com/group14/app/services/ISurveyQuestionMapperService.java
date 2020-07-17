package com.group14.app.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.group14.app.models.SurveyQuestionMapper;

public interface ISurveyQuestionMapperService {

	ArrayList<SurveyQuestionMapper> getSurveyQuestions(int surveyId) throws SQLException;

	int[] deleteSurveyQuestion(int responseId) throws SQLException;

	int addSurveyQuestion(int surveyId, int questionId) throws SQLException;

}
