package com.group14.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

public class QuestionsTest {

	@Test
	void setQuestionIdTest() {
		Questions question = new Questions();
		question.setQuestionId(1);
		assertTrue(question.questionId == 1);
	}
	
	@Test
	void getQuestionIdTest() {
		Questions question = new Questions();
		question.setQuestionId(1);
		assertTrue(question.questionId == 1);
	}

	@Test
	void setInstructorIdTest() {
		Questions question = new Questions();
		question.setInstructorId("B00100007");
		assertTrue(question.instructorId.equals("B00100007"));
	}
	
	@Test
	void getInstructorIdTest() {
		Questions question = new Questions();
		question.setInstructorId("B00100007");
		assertTrue(question.instructorId.equals("B00100007"));
	}
	
	@Test
	void setTitleTest() {
		Questions question = new Questions();
		question.setTitle("ABCD");
		assertTrue(question.title.equals("ABCD"));
	}
	
	@Test
	void getTitleTest() {
		Questions question = new Questions();
		question.setTitle("ABCD");
		assertTrue(question.title.equals("ABCD"));
	}
	
	@Test
	void setTextTest() {
		Questions question = new Questions();
		question.setText("ABCD");
		assertTrue(question.text.equals("ABCD"));
	}
	
	@Test
	void getTextTest() {
		Questions question = new Questions();
		question.setText("ABCD");
		assertTrue(question.text.equals("ABCD"));
	}
	
	@Test
	void setTypeTest() {
		Questions question = new Questions();
		question.setType("ABCD");
		assertTrue(question.type.equals("ABCD"));
	}
	
	@Test
	void getTypeTest() {
		Questions question = new Questions();
		question.setType("ABCD");
		assertTrue(question.type.equals("ABCD"));
	}
	
	@Test
	void setCreatedDateTest() {
		Questions question = new Questions();
		question.setCreatedDate(Timestamp.valueOf("2016-11-16 06:43:19.77"));
		assertEquals(question.createdDate, Timestamp.valueOf("2016-11-16 06:43:19.77"));
	}
	
	@Test
	void getCreatedDateTest() {
		Questions question = new Questions();
		question.setCreatedDate(Timestamp.valueOf("2016-11-16 06:43:19.77"));
		assertEquals(question.createdDate, Timestamp.valueOf("2016-11-16 06:43:19.77"));
	}
	
}
