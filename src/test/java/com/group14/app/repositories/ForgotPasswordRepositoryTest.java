package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group14.app.models.Courses;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

public class ForgotPasswordRepositoryTest {

	@InjectMocks
	private ForgotPasswordRepository fPR;

	@Mock
	private CRUDRepository<SQLInput> mockDB;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void readPass() throws SQLException {

		List<HashMap<String, Object>> usersData = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row = new HashMap<String, Object>();
		row.put("password", "password");

		usersData.add(row);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(usersData);

		final String pass = this.fPR.readPass("B00100002");
		assertEquals("password", pass);
	}

	@Test
	void readEmail() throws SQLException {

		List<HashMap<String, Object>> usersData = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row = new HashMap<String, Object>();
		row.put("email", "abc@dal.ca");

		usersData.add(row);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(usersData);

		final String email = this.fPR.readEmail("B00100002");
		assertEquals("abc@dal.ca", email);
	}
}
