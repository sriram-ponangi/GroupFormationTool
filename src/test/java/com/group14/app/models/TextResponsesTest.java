package com.group14.app.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TextResponsesTest {

	@Test
	void setQuestionIdTest() {
		TextResponses response = new TextResponses();
		response.setTqid("2");
		assertTrue(response.getTqid().equals("2"));
	}

	@Test
	void getQuestionIdTest() {
		TextResponses response = new TextResponses();
		response.setTqid("2");
		assertTrue(response.getTqid().equals("2"));
	}

	@Test
	void setResponseIdTest() {
		TextResponses response = new TextResponses();
		response.setResponseId(1);
		assertTrue(response.getResponseId() == 1);
	}

	@Test
	void getResponseIdTest() {
		TextResponses response = new TextResponses();
		response.setResponseId(1);
		assertTrue(response.getResponseId() == 1);
	}

	@Test
	void setUserIdTest() {
		TextResponses response = new TextResponses();
		response.setUserId("B00100001");
		assertTrue(response.getUserId().equals("B00100001"));
	}

	@Test
	void getUserIdTest() {
		TextResponses response = new TextResponses();
		response.setUserId("B00100001");
		assertTrue(response.getUserId().equals("B00100001"));
	}

	@Test
	void setNumAnswerTest() {
		TextResponses response = new TextResponses();
		response.setTextanswer("Java");
		assertTrue(response.getTextanswer().equals("Java"));
	}

	@Test
	void getNumAnswerTest() {
		TextResponses response = new TextResponses();
		response.setTextanswer("Java");
		assertTrue(response.getTextanswer().equals("Java"));
	}
}