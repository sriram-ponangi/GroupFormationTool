package com.group14.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

public class QuestionsTest {

	@Test
	void setQuestionIdTest() {
		AllQuestions question = new AllQuestions();
		question.setQid(1);
		assertTrue(question.getQid() == 1);
	}

	@Test
	void getQuestionIdTest() {
		AllQuestions question = new AllQuestions();
		question.setQid(1);
		assertTrue(question.getQid() == 1);
	}

	@Test
	void setInstructorIdTest() {
		AllQuestions question = new AllQuestions();
		question.setInstructor_id("B00100007");
		assertTrue(question.getInstructor_id().equals("B00100007"));
	}

	@Test
	void getInstructorIdTest() {
		AllQuestions question = new AllQuestions();
		question.setInstructor_id("B00100007");
		assertTrue(question.getInstructor_id().equals("B00100007"));
	}

	@Test
	void setTitleTest() {
		AllQuestions question = new AllQuestions();
		question.setTitle("ABCD");
		assertTrue(question.getTitle().equals("ABCD"));
	}

	@Test
	void getTitleTest() {
		AllQuestions question = new AllQuestions();
		question.setTitle("ABCD");
		assertTrue(question.getTitle().equals("ABCD"));
	}

	@Test
	void setTextTest() {
		AllQuestions question = new AllQuestions();
		question.setText("ABCD");
		assertTrue(question.getText().equals("ABCD"));
	}

	@Test
	void getTextTest() {
		AllQuestions question = new AllQuestions();
		question.setText("ABCD");
		assertTrue(question.getText().equals("ABCD"));
	}

	@Test
	void setTypeTest() {
		AllQuestions question = new AllQuestions();
		question.setType("ABCD");
		assertTrue(question.getType().equals("ABCD"));
	}

	@Test
	void getTypeTest() {
		AllQuestions question = new AllQuestions();
		question.setType("ABCD");
		assertTrue(question.getType().equals("ABCD"));
	}

	@Test
	void setCreatedDateTest() {
		AllQuestions question = new AllQuestions();
		question.setCreatedDate(Timestamp.valueOf("2016-11-16 06:43:19.77"));
		assertEquals(question.getCreatedDate(), Timestamp.valueOf("2016-11-16 06:43:19.77"));
	}

	@Test
	void getCreatedDateTest() {
		AllQuestions question = new AllQuestions();
		question.setCreatedDate(Timestamp.valueOf("2016-11-16 06:43:19.77"));
		assertEquals(question.getCreatedDate(), Timestamp.valueOf("2016-11-16 06:43:19.77"));
	}

}
