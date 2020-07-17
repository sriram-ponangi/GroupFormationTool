package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.List;
import com.group14.app.models.Survey;
import com.group14.app.models.SurveyQuestionMapper;

public interface ISurveyRepository {

	Survey getSurveyInfo(String courseId) throws SQLException;

	List<SurveyQuestionMapper> getSurveyQuestionsInfo(int surveyId) throws SQLException;

	int publishSurvey(int surveyId) throws SQLException;

	int unpublishSurvey(int surveyId) throws SQLException;

	int createSurvey(Survey survey) throws SQLException;

}
