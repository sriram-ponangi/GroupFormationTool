package com.group14.app.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.group14.app.repositories.AnswerManagerRepository;

public class AnswerManagerServiceTest {

	@InjectMocks
	private AnswerManagerService aMS;

	@Mock
	private AnswerManagerRepository aMR;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteData() throws SQLException {
		int id = 1;
		doNothing().when(this.aMR).deleteQuestionAsTransaction(id);
		aMS.deleteData(id);

		verify(aMR, times(1)).deleteQuestionAsTransaction(1);
	}
}
