package com.group14.app.repositories;

import java.util.List;

import com.group14.app.models.GroupFormationAlgoRule;
import com.group14.app.models.SurveyRuleMapper;

public interface IGroupFormationAlgorithmRepository {
	List<GroupFormationAlgoRule> getAllGroupFormationAlgoRules();
		
	boolean saveAlgorithmRules(List<SurveyRuleMapper> surveyQuestionRules);
	
	SurveyRuleMapper getSavedAlgorithmRules(Integer surveyResponseId);
}
