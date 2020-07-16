package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class ChoiceRepositoryTest {

	public static final String instructorId = "B00100007";
	public static final String title = "SDC";
	public static final String text = "Difficulty";
	public static final String type = "MCQ";
	public static final String displayText = "Easy";
	public static final String savedAs = "1";

	ChoicesRepository crmock = mock(ChoicesRepository.class);

	@Test
	void addQuestionSingleTest() throws SQLException {

		when(crmock.addQuestionSingle(instructorId, title, text, type)).thenReturn(true);
		assertTrue(crmock.addQuestionSingle(instructorId, title, text, type), "Failed");
		verify(crmock).addQuestionSingle(instructorId, title, text, type);

	}

	@Test
	void addQuestionMultipleTest() throws SQLException {

		when(crmock.addQuestionMultiple(instructorId, title, text, type, displayText, savedAs)).thenReturn(true);
		assertTrue(crmock.addQuestionMultiple(instructorId, title, text, type, displayText, savedAs), "Failed");
		verify(crmock).addQuestionMultiple(instructorId, title, text, type, displayText, savedAs);

	}

}
