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

public class SurveyRepositoryTest {

	@InjectMocks
	SurveyRepository surveyRepository;

	@Mock
	private CRUDRepository<SQLInput> mockDB;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getSurveyInfo_BasicSuccessCase() throws SQLException {
		List<HashMap<String, Object>> mockDBResponse = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row1 = new HashMap<>();
		row1.put("survey_id", 1);
		row1.put("course_id", "CSCI1001");
		row1.put("published", 1);
		row1.put("group_size", 4);
		mockDBResponse.add(row1);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(mockDBResponse);

		Survey response = surveyRepository.getSurveyInfo("CSCI1001");
		assertEquals(1, response.getSurveyId());
		assertEquals("CSCI1001", response.getCourseId());
		assertEquals(1, response.getPublished());
		assertEquals(4, response.getGroupSize());
	}

	@Test
	public void getSurveyInfo_BasicFailedCase() throws SQLException {
		List<HashMap<String, Object>> mockDBResponse = new ArrayList<HashMap<String, Object>>();

		when(mockDB.readData(any(SQLInput.class))).thenReturn(mockDBResponse);

		Survey response = surveyRepository.getSurveyInfo("CSCI1001");
		assertEquals(0, response.getSurveyId());
		assertEquals(null, response.getCourseId());
		assertEquals(0, response.getPublished());
		assertEquals(0, response.getGroupSize());
	}

	@Test
	public void getSurveyQuestionsInfoTest_BasicSuccessCase() throws SQLException {
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

		List<SurveyQuestionMapper> response = surveyRepository.getSurveyQuestionsInfo(1);
		assertEquals(2, response.size());
		assertEquals(1, response.get(0).getQuestionId());
		assertEquals(1, response.get(0).getResponseId());
		assertEquals(2, response.get(1).getQuestionId());
		assertEquals(2, response.get(1).getResponseId());
	}

	@Test
	public void getSurveyQuestionsInfoTest_BasicFailedCase() throws SQLException {
		List<HashMap<String, Object>> mockDBResponse = new ArrayList<HashMap<String, Object>>();

		when(mockDB.readData(any(SQLInput.class))).thenReturn(mockDBResponse);

		List<SurveyQuestionMapper> response = surveyRepository.getSurveyQuestionsInfo(1);
		assertEquals(0, response.size());

	}

	@Test
	public void publishSurvey_BasicSuccessCase() throws SQLException {
		int mockDBResponse = 1;

		when(mockDB.save(any(SQLInput.class))).thenReturn(mockDBResponse);

		int response = surveyRepository.publishSurvey(any(Integer.class));
		assertEquals(1, response);
	}

	@Test
	public void publishSurvey_BasicFailedCase() throws SQLException {
		int mockDBResponse = 0;

		when(mockDB.save(any(SQLInput.class))).thenReturn(mockDBResponse);

		int response = surveyRepository.publishSurvey(any(Integer.class));
		assertEquals(0, response);
	}

	@Test
	public void unpublishSurvey_BasicSuccessCase() throws SQLException {
		int mockDBResponse = 1;

		when(mockDB.save(any(SQLInput.class))).thenReturn(mockDBResponse);

		int response = surveyRepository.unpublishSurvey(any(Integer.class));
		assertEquals(1, response);
	}

	@Test
	public void unpublishSurvey_BasicFailedCase() throws SQLException {
		int mockDBResponse = 0;

		when(mockDB.save(any(SQLInput.class))).thenReturn(mockDBResponse);

		int response = surveyRepository.unpublishSurvey(any(Integer.class));
		assertEquals(0, response);
	}

	@Test
	public void createSurvey_BasicSuccessCase() throws SQLException {
		Survey survey = new Survey(1, 1, 1, "CSCI1001");
		int mockDBResponse = 1;

		when(mockDB.save(any(SQLInput.class))).thenReturn(mockDBResponse);

		int response = surveyRepository.createSurvey(survey);

		assertEquals(1, response);
	}

	@Test
	public void createSurvey_BasicFailedCase() throws SQLException {
		Survey survey = new Survey(1, 1, 1, "CSCI1001");
		int mockDBResponse = 0;

		when(mockDB.save(any(SQLInput.class))).thenReturn(mockDBResponse);

		int response = surveyRepository.createSurvey(survey);

		assertEquals(0, response);
	}
}
