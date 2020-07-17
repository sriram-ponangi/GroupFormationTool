package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group14.app.models.SQLInput;
import com.group14.app.models.Survey;
import com.group14.app.models.SurveyQuestionMapper;
import com.group14.app.utils.CRUDRepository;

public class SurveyQuestionMapperRepositoryTest {

	@InjectMocks
	SurveyQuestionMapperRepository surveyQuestionMapperRepository;

	@Mock
	private CRUDRepository<SQLInput> mockDB;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllSurveyQuestions_BasicSuccessCase() throws SQLException {
		List<HashMap<String, Object>> mockDBResponse = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row1 = new HashMap<>();
		row1.put("question_id", 1);
		row1.put("response_id", 1);
		row1.put("survey_id", 1);
		HashMap<String, Object> row2 = new HashMap<>();
		row2.put("question_id", 2);
		row2.put("response_id", 2);
		row2.put("survey_id", 1);
		mockDBResponse.add(row1);
		mockDBResponse.add(row2);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(mockDBResponse);

		List<SurveyQuestionMapper> response = surveyQuestionMapperRepository.getAllSurveyQuestions(1);
		assertEquals(2, response.size());
		assertEquals(1, response.get(0).getQuestionId());
		assertEquals(1, response.get(0).getResponseId());
		assertEquals(2, response.get(1).getQuestionId());
		assertEquals(2, response.get(1).getResponseId());
	}

	@Test
	public void getAllSurveyQuestions_BasicFailureCase() throws SQLException {
		List<HashMap<String, Object>> mockDBResponse = new ArrayList<HashMap<String, Object>>();

		when(mockDB.readData(any(SQLInput.class))).thenReturn(mockDBResponse);

		List<SurveyQuestionMapper> response = surveyQuestionMapperRepository.getAllSurveyQuestions(1);
		assertEquals(0, response.size());
	}

	@Test
	public void addSurveyQuestion_BasicSuccessCase() throws SQLException {
		SurveyQuestionMapper surveyQuestionMapper = new SurveyQuestionMapper(1, 1, 1);
		int mockDBResponse = 1;

		when(mockDB.save(any(SQLInput.class))).thenReturn(mockDBResponse);

		int response = surveyQuestionMapperRepository.addSurveyQuestion(surveyQuestionMapper.getQuestionId(),
				surveyQuestionMapper.getSurveyId());

		assertEquals(1, response);
	}

	@Test
	public void addSurveyQuestion_BasicFailedCase() throws SQLException {
		SurveyQuestionMapper surveyQuestionMapper = new SurveyQuestionMapper(1, 1, 1);
		int mockDBResponse = 0;

		when(mockDB.save(any(SQLInput.class))).thenReturn(mockDBResponse);

		int response = surveyQuestionMapperRepository.addSurveyQuestion(surveyQuestionMapper.getQuestionId(),
				surveyQuestionMapper.getSurveyId());

		assertEquals(0, response);
	}

	@Test
	public void deleteSurveyQuestion_BasicSuccessCase() throws SQLException {
		SurveyQuestionMapper surveyQuestionMapper = new SurveyQuestionMapper(1, 1, 1);
		int[] mockDBResponse = new int[4];
		mockDBResponse[0] = 1;

		when(mockDB.saveTransaction(any())).thenReturn(mockDBResponse);

		int[] response = surveyQuestionMapperRepository.deleteSurveyQuestion(surveyQuestionMapper.getResponseId());

		assertEquals(1, response[0]);
	}

	@Test
	public void deleteSurveyQuestion_BasicFailedCase() throws SQLException {
		SurveyQuestionMapper surveyQuestionMapper = new SurveyQuestionMapper(1, 1, 1);
		int[] mockDBResponse = new int[4];
		mockDBResponse[0] = 1;
		when(mockDB.saveTransaction(any())).thenReturn(mockDBResponse);

		int[] response = surveyQuestionMapperRepository.deleteSurveyQuestion(surveyQuestionMapper.getResponseId());

		assertEquals(1, response[0]);
	}
}
