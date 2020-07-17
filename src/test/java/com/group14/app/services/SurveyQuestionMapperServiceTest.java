package com.group14.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group14.app.models.SurveyQuestionMapper;
import com.group14.app.repositories.SurveyQuestionMapperRepository;

public class SurveyQuestionMapperServiceTest {

	@InjectMocks
	private SurveyQuestionMapperService sQMS;

	@Mock
	private SurveyQuestionMapperRepository sQMR;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getSurveyQuestions() throws SQLException {
		int surveyId = 1;
		ArrayList<SurveyQuestionMapper> mockDBResponse = new ArrayList<SurveyQuestionMapper>();
		mockDBResponse.add(new SurveyQuestionMapper(1, 1, 1));
		mockDBResponse.add(new SurveyQuestionMapper(2, 2, 1));

		when(this.sQMR.getAllSurveyQuestions(surveyId)).thenReturn(mockDBResponse);

		when(this.sQMR.getAllSurveyQuestions(surveyId)).thenReturn(mockDBResponse);
		ArrayList<SurveyQuestionMapper> response = sQMS.getSurveyQuestions(surveyId);

		assertEquals(mockDBResponse, response);
	}

	@Test
	public void deleteSurveyQuestion() throws SQLException {
		SurveyQuestionMapper surveyQuestionMapper = new SurveyQuestionMapper(1, 1, 1);
		int[] mockDBResponse = new int[4];
		mockDBResponse[0] = 1;

		when(this.sQMR.deleteSurveyQuestion(surveyQuestionMapper.getResponseId())).thenReturn(mockDBResponse);

		int[] response = sQMS.deleteSurveyQuestion(surveyQuestionMapper.getResponseId());

		assertEquals(1, response[0]);
	}

	@Test
	public void createSurveyQuestion() throws SQLException {
		SurveyQuestionMapper surveyQuestionMapper = new SurveyQuestionMapper(1, 1, 1);
		int mockDBResponse = 1;

		when(this.sQMR.addSurveyQuestion(surveyQuestionMapper.getQuestionId(), surveyQuestionMapper.getSurveyId()))
				.thenReturn(mockDBResponse);

		int response = sQMS.addSurveyQuestion(surveyQuestionMapper.getQuestionId(), surveyQuestionMapper.getSurveyId());

		assertEquals(1, response);
	}
}
