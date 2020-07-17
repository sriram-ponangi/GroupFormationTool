package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group14.app.models.SurveyQuestionMapper;

public interface ISurveyQuestionMapperRepository {

	ArrayList<SurveyQuestionMapper> getAllSurveyQuestions(int surveyId) throws SQLException;

	int[] deleteSurveyQuestion(int responseId) throws SQLException;

	int addSurveyQuestion(int surveyId, int questionId) throws SQLException;

}
