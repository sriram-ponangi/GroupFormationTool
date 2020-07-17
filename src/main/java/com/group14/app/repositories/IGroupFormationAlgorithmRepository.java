package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.List;

import com.group14.app.models.GroupFormationAlgoRule;
import com.group14.app.models.SurveyRuleMapper;

public interface IGroupFormationAlgorithmRepository {
	List<GroupFormationAlgoRule> getAllGroupFormationAlgoRules() throws SQLException;

	boolean saveAlgorithmRules(List<SurveyRuleMapper> surveyQuestionRules) throws SQLException;

	SurveyRuleMapper getSavedAlgorithmRules(Integer surveyResponseId) throws SQLException;
}
