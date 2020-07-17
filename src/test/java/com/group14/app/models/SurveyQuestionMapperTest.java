package com.group14.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SurveyQuestionMapperTest {

	@Test
	public void isValidSurveyQuestionMapperTest() {

		SurveyQuestionMapper surveyQuestionMapper = new SurveyQuestionMapper(1, 1, -1);
		assertEquals(false, surveyQuestionMapper.isValidSurveyQuestionMapper());// Testing for invalid surveyID - Must
																				// be positive number

		surveyQuestionMapper = new SurveyQuestionMapper(1, 1, 1);
		assertEquals(true, surveyQuestionMapper.isValidSurveyQuestionMapper());// Testing for valid surveyID

		surveyQuestionMapper = new SurveyQuestionMapper(1, -1, 1);
		assertEquals(false, surveyQuestionMapper.isValidSurveyQuestionMapper());// Testing for invalid questionID

		surveyQuestionMapper = new SurveyQuestionMapper(1, 1, 1);
		assertEquals(true, surveyQuestionMapper.isValidSurveyQuestionMapper());// Testing for valid questionID

		surveyQuestionMapper = new SurveyQuestionMapper(-1, 1, 1);
		assertEquals(false, surveyQuestionMapper.isValidSurveyQuestionMapper());// Testing for invalid responseID

		surveyQuestionMapper = new SurveyQuestionMapper(1, 1, 1);
		assertEquals(true, surveyQuestionMapper.isValidSurveyQuestionMapper());// Testing for valid responseID

	}
}
