package com.group14.app.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.group14.app.models.AllQuestions;
import com.group14.app.models.Survey;
import com.group14.app.repositories.ISurveyRepository;

@Service
public class SurveyService implements ISurveyService {

	private ISurveyRepository iSurveyRepository;

	public SurveyService(ISurveyRepository iSurveyRepository) {
		this.iSurveyRepository = iSurveyRepository;
	}

	@Override
	public int getSurveyId(String courseId) throws SQLException {
		Survey survey = new Survey();
		try {
			survey = iSurveyRepository.getSurveyInfo(courseId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (survey == null) {
			return 0;
		}
		return survey.getSurveyId();
	}

	@Override
	public List<AllQuestions> getAllSurveyQuestions(String surveyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSurveyPublished(String surveyId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void publishSurvey(String surveyId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int createSurvey(Survey survey) throws SQLException {
		int rowUpdated = 0;
		try {
			rowUpdated = iSurveyRepository.createSurvey(survey);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowUpdated;
	}

}
