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

import com.group14.app.models.Courses;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

public class CourseRepoTest {

	@InjectMocks
	private CourseRepository cR;

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
		row.put("course_id", "course_id");
		row.put("name", "name");
		row.put("term", "term");
		row.put("year", "year");
		row.put("description", "description");
		row.put("enabled", 1);
		usersData.add(row);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(usersData);

		final List<Courses> course = this.cR.list();
		assertEquals("course_id", course.get(0).getCid());
		assertEquals("name", course.get(0).getName());
		assertEquals("term", course.get(0).getTerm());
		assertEquals("year", course.get(0).getYear());
		assertEquals("description", course.get(0).getDescription());
		assertEquals(1, course.get(0).getEnable());
	}

	@Test
	void addCourse() throws SQLException {
		Courses c = new Courses();
		cR = mock(CourseRepository.class);
		doNothing().when(this.cR).addCourse(c);

		cR.addCourse(c);

		verify(cR, times(1)).addCourse(c);

	}

	@Test
	void deleteCourse() throws SQLException {
		Courses c = new Courses();
		cR = mock(CourseRepository.class);
		doNothing().when(this.cR).deleteCourse(c);

		cR.deleteCourse(c);

		verify(cR, times(1)).deleteCourse(c);
	}

}
