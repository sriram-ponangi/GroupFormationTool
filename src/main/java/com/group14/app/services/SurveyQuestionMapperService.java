package com.group14.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.group14.app.models.SurveyQuestionMapper;
import com.group14.app.repositories.ISurveyQuestionMapperRepository;

@Service
public class SurveyQuestionMapperService implements ISurveyQuestionMapperService {

	private ISurveyQuestionMapperRepository iSurveyQuestionMapperRepository;

	public SurveyQuestionMapperService(ISurveyQuestionMapperRepository iSurveyQuestionMapperRepository) {
		this.iSurveyQuestionMapperRepository = iSurveyQuestionMapperRepository;
	}

	@Override
	public ArrayList<SurveyQuestionMapper> getSurveyQuestions(int surveyId) throws SQLException {

		ArrayList<SurveyQuestionMapper> surveyQuestions = new ArrayList<SurveyQuestionMapper>();
		try {
			surveyQuestions = iSurveyQuestionMapperRepository.getAllSurveyQuestions(surveyId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return surveyQuestions;
	}

	@Override
	public int[] deleteSurveyQuestion(int responseId) throws SQLException {

		int[] rowsUpdated = iSurveyQuestionMapperRepository.deleteSurveyQuestion(responseId);

		return rowsUpdated;
	}

	@Override
	public int addSurveyQuestion(int surveyId, int questionId) throws SQLException {

		int rowsUpdated = iSurveyQuestionMapperRepository.addSurveyQuestion(surveyId, questionId);

		return rowsUpdated;

	}

}
