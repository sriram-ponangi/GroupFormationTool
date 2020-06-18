package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

public class QuestionManagerRepositoryTest {

	@InjectMocks
	private QuestionManagerRepository qMR;

	@Mock
	private CRUDRepository<SQLInput> mockDB;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void findRole() {
		List<HashMap<String, Object>> usersData = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row = new HashMap<String, Object>();
		row.put("instructor_id", "B00100001");

		usersData.add(row);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(usersData);

		final String id = this.qMR.FindRoleForID("1");
		assertEquals("B00100001", id);
	}

}
