package com.group14.app.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.group14.app.models.AllQuestions;
import com.group14.app.models.SurveyAlgorithmInfo;
import com.group14.app.models.SurveyQuestionMapper;
import com.group14.app.models.SurveyRuleMapper;

public interface IGroupFormationAlgorithmService {
	List<AllQuestions> getAllSurveyQuestionDetailsById(List<Integer> questionIds) throws SQLException;

	Map<Integer, Integer> mapQuestionIdWithResponseIdForSurvey(List<SurveyQuestionMapper> surveyQuestions)
			throws SQLException;

	boolean saveSurveyAlgorithm(SurveyAlgorithmInfo info) throws SQLException;

	Map<Integer, SurveyRuleMapper> mapQuestionIdWithSavedAlgorithmRules(List<SurveyQuestionMapper> surveyQuestions)
			throws SQLException;
}
