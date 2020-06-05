package com.group14.app.repositories;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.group14.app.models.Course;

class CourseRepositoryTest {

	private CoursesStudRepository cr=mock(CoursesStudRepository.class);

	ArrayList<Course> list=new ArrayList<>();
	Course c=new Course();
	
	
	@Test
	
	void showCourseTest() {
		
		when(this.cr.getAllCourse("B00100001")).thenReturn(list);
	}
	

	@Test
	
	void showSpecificCourseTest() {
		
		when(this.cr.getAssignedCourse("B00100001")).thenReturn(list);
	}
	
}
