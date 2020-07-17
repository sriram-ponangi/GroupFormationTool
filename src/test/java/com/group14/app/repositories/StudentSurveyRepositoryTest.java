package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import com.group14.app.models.AllQuestions;

import org.junit.jupiter.api.Test;

class StudentSurveyRepositoryTest {

	public static final String userId = "B00100007";
	public static final String cid = "CSCI1001";
	public static final String qid = "7";
	public static final Integer sid = 1;
	public static final String response = "SDC";

	StudentSurveyRepository mock = mock(StudentSurveyRepository.class);

	ArrayList<AllQuestions> questionsList = new ArrayList<>();
	AllQuestions allquestions = new AllQuestions();

	@Test
	void getSurveyTest() throws SQLException {

		when(this.mock.getSurvey(cid)).thenReturn(questionsList);
	}

	@Test
	void getSurveyIDTest() throws SQLException {

		when(this.mock.getSurveyID(cid)).thenReturn(sid);
	}

	@Test
	void addNumericSurveyResponseTest() throws SQLException {

		when(mock.addNumericSurveyResponse(userId, cid, response, qid)).thenReturn(true);
		assertTrue(mock.addNumericSurveyResponse(userId, cid, response, qid), "Failed");
		verify(mock).addNumericSurveyResponse(userId, cid, response, qid);

	}

	@Test
	void addTextSurveyResponseTest() throws SQLException {

		when(mock.addTextSurveyResponse(userId, cid, response, qid)).thenReturn(true);
		assertTrue(mock.addTextSurveyResponse(userId, cid, response, qid), "Failed");
		verify(mock).addTextSurveyResponse(userId, cid, response, qid);

	}

	@Test
	void isSurveyPublishedTest() throws SQLException {

		when(mock.isSurveyPublished(cid)).thenReturn(true);
		assertTrue(mock.isSurveyPublished(cid), "Failed");
		verify(mock).isSurveyPublished(cid);

	}

}
