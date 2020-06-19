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
import com.group14.app.repositories.CourseRoleMapperRepository;

public class CourseRoleMapperServiceTest {

	@InjectMocks
	private CourseRoleMapperService cRMS;

	@Mock
	private CourseRoleMapperRepository cRMR;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void addInstructor() throws SQLException {
		doNothing().when(this.cRMR).addCourseI("B00100001", "CSCI1001");
		cRMS.addCourseInstructor("B00100001", "CSCI1001");

		verify(cRMR, times(1)).addCourseI("B00100001", "CSCI1001");
	}
}
