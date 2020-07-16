package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group14.app.models.AppUser;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

public class StudentEnrollmentRepositoryTest {

	@InjectMocks
	private StudentEnrollmentRepository studentEnrollmentRepository;

	@Mock
	private CRUDRepository<SQLInput> mockDB;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void enrollStudentToCourseTest() throws SQLException {
		AppUser appUser = new AppUser("userId", "password", "email", "firstName", "lastName", 1);

		when(mockDB.existsById(any(SQLInput.class))).thenReturn(false).thenReturn(false).thenReturn(false);
		int[] expectedResponse = new int[] { 1, 1, 1 };
		when(mockDB.saveTransaction(any())).thenReturn(expectedResponse);

		int[] response = this.studentEnrollmentRepository.enrollStudentToCourse(appUser, any(String.class));
		assertEquals(expectedResponse.length, response.length);

		when(mockDB.existsById(any(SQLInput.class))).thenReturn(true).thenReturn(true).thenReturn(true);
		expectedResponse = new int[] { 1, 1, 1 };
		when(mockDB.saveTransaction(any())).thenReturn(expectedResponse);
		response = this.studentEnrollmentRepository.enrollStudentToCourse(appUser, any(String.class));
		assertEquals(expectedResponse.length, response.length);
	}
}
