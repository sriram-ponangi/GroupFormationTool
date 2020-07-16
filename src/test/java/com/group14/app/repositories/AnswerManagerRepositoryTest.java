package com.group14.app.repositories;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.group14.app.models.SQLInput;

public class AnswerManagerRepositoryTest {

	private AnswerManagerRepository aMR = mock(AnswerManagerRepository.class);

	@Test
	void deleteData() throws SQLException {
		int id = 1;
		doNothing().when(this.aMR).deleteQuestionAsTransaction(id);
		aMR.deleteQuestionAsTransaction(1);

		verify(aMR, times(1)).deleteQuestionAsTransaction(1);
	}
}
