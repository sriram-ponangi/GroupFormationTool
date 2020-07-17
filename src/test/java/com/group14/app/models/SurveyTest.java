package com.group14.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SurveyTest {

	@Test
	public void isValidSurveyTest() {

		Survey survey = new Survey(1, 1, -1, "CSCI1001");
		assertEquals(false, survey.isValidSurvey());// Testing for invalid groupSize - Must be positive number

		survey = new Survey(-1, 1, 1, "CSCI1001");
		assertEquals(false, survey.isValidSurvey());// Testing for invalid surveyID

		survey = new Survey(1, -1, 1, "CSCI1001");
		assertEquals(false, survey.isValidSurvey());// Testing for invalid published value

		survey = new Survey(1, 1, 1, "1001");
		assertEquals(false, survey.isValidSurvey());// Testing for invalid courseID

		survey = new Survey(1, 1, 1, "CSCI1001");
		assertEquals(true, survey.isValidSurvey());// Testing for valid courseID

		survey = new Survey(1, 1, 3, "CSCI1001");
		assertEquals(true, survey.isValidSurvey());// Testing for valid groupSize

		survey = new Survey(2, 1, 1, "CSCI1001");
		assertEquals(true, survey.isValidSurvey());// Testing for valid surveyID

		survey = new Survey(1, 1, 1, "CSCI1001");
		assertEquals(true, survey.isValidSurvey());// Testing for valid published value
	}
}
