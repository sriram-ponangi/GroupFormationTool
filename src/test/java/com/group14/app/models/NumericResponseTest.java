package com.group14.app.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumericResponseTest {

	@Test
	void setQuestionIdTest() {
		NumericResponses response = new NumericResponses();
		response.setQid("2");
		assertTrue(response.getQid().equals("2"));
	}

	@Test
	void getQuestionIdTest() {
		NumericResponses response = new NumericResponses();
		response.setQid("2");
		assertTrue(response.getQid().equals("2"));
	}

	@Test
	void setResponseIdTest() {
		NumericResponses response = new NumericResponses();
		response.setResponseId(1);
		assertTrue(response.getResponseId() == 1);
	}

	@Test
	void getResponseIdTest() {
		NumericResponses response = new NumericResponses();
		response.setResponseId(1);
		assertTrue(response.getResponseId() == 1);
	}

	@Test
	void setUserIdTest() {
		NumericResponses response = new NumericResponses();
		response.setUserId("B00100001");
		assertTrue(response.getUserId().equals("B00100001"));
	}

	@Test
	void getUserIdTest() {
		NumericResponses response = new NumericResponses();
		response.setUserId("B00100001");
		assertTrue(response.getUserId().equals("B00100001"));
	}

	@Test
	void setNumAnswerTest() {
		NumericResponses response = new NumericResponses();
		response.setNumanswer("34");
		assertTrue(response.getNumanswer().equals("34"));
	}

	@Test
	void getNumAnswerTest() {
		NumericResponses response = new NumericResponses();
		response.setNumanswer("34");
		assertTrue(response.getNumanswer().equals("34"));
	}
}