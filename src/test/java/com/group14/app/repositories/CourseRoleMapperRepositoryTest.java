package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.group14.app.models.CourseRoleMapper;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

public class CourseRoleMapperRepositoryTest {

	@InjectMocks
	private CourseRoleMapperRepository cRM;

	@Mock
	private CRUDRepository<SQLInput> mockDB;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void ListCourse() throws SQLException {

		List<HashMap<String, Object>> usersData = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row = new HashMap<String, Object>();
		row.put("role_id", "role_id");
		row.put("user_id", "user_id");
		row.put("course_id", "course_id");
		usersData.add(row);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(usersData);

		final List<CourseRoleMapper> course = this.cRM.list();
		assertEquals("role_id", course.get(0).getRole_id());
		assertEquals("user_id", course.get(0).getUser_id());
		assertEquals("course_id", course.get(0).getCourse_id());

	}

	@Test
	void addInstructor() throws SQLException {
		cRM = mock(CourseRoleMapperRepository.class);
		doNothing().when(this.cRM).addCourseI("B00100001", "CSCI1001");

		cRM.addCourseI("B00100001", "CSCI1001");

		verify(cRM, times(1)).addCourseI("B00100001", "CSCI1001");
	}
}
