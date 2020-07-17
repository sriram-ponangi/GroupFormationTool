package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.List;

import com.group14.app.models.GroupFormationAlgoRule;
import com.group14.app.models.NumericAnswers;
import com.group14.app.models.SurveyRuleMapper;

public interface IGroupFormationResultsRepository {

	List<NumericAnswers> getAllStudentResponses(String courseId) throws SQLException;

	List<SurveyRuleMapper> getAlgorithmRuleFromCourse(String courseId) throws SQLException;

	int getGroupSize(String courseId) throws SQLException;

}
