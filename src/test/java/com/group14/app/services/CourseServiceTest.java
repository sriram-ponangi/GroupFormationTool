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

import com.group14.app.models.Courses;
import com.group14.app.repositories.CourseRepository;

public class CourseServiceTest {

	@InjectMocks
	private CourseService cS;

	@Mock
	private CourseRepository cR;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addCourse() throws SQLException {
		Courses courses = new Courses();
		doNothing().when(this.cR).addCourse(courses);
		cS.addCourse(courses);

		verify(cR, times(1)).addCourse(courses);
	}

	@Test
	public void deleteCourse() throws SQLException {
		Courses courses = new Courses();
		doNothing().when(this.cR).deleteCourse(courses);
		cS.deleteCourse(courses);

		verify(cR, times(1)).deleteCourse(courses);
	}

}
