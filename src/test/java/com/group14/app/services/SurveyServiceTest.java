package com.group14.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group14.app.models.Survey;
import com.group14.app.repositories.SurveyRepository;

public class SurveyServiceTest {

	@InjectMocks
	private SurveyService sS;

	@Mock
	private SurveyRepository sR;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getSurveyId() throws SQLException {

		Survey survey = new Survey(1, 1, 1, "CSCI1001");
		String courseId = "CSCI1001";
		when(this.sR.getSurveyInfo(courseId)).thenReturn(survey);

		int response = sS.getSurveyId(courseId);

		assertEquals(survey.getSurveyId(), response);
	}

	@Test
	public void createSurvey() throws SQLException {
		Survey survey = new Survey(1, 1, 1, "CSCI1001");
		int mockDBResponse = 1;

		when(this.sR.createSurvey(survey)).thenReturn(mockDBResponse);

		int response = sS.createSurvey(survey);

		assertEquals(1, response);
	}
}
