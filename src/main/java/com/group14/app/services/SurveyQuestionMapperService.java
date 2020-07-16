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
	public int deleteSurveyQuestion(int surveyId, int questionId) throws SQLException {
		
		int rowsUpdated = 0;
		try {
			rowsUpdated = iSurveyQuestionMapperRepository.deleteSurveyQuestion(surveyId, questionId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(rowsUpdated == 0) {
			return 0;
		}
		else 
		{
			return rowsUpdated;
		}
	}

	@Override
	public int addSurveyQuestion(int surveyId, int questionId) throws SQLException {
		int rowsUpdated = 0;
		try {
			rowsUpdated = iSurveyQuestionMapperRepository.addSurveyQuestion(surveyId, questionId);
			
			if(rowsUpdated == 0) {
				return 0;
			}
			else
			{
				return rowsUpdated;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	
}
