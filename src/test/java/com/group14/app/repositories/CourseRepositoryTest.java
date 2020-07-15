package com.group14.app.repositories;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.group14.app.models.Courses;

class CourseRepositoryTest {

	private CoursesStudRepository cr = mock(CoursesStudRepository.class);

	ArrayList<Courses> list = new ArrayList<>();
	Courses c = new Courses();

	@Test
	void showCourseTest() throws SQLException {

		when(this.cr.getAllCourse("B00100001")).thenReturn(list);
	}

	@Test
	void showSpecificCourseTest() throws SQLException {

		when(this.cr.getAssignedCourse("B00100001")).thenReturn(list);
	}

}
