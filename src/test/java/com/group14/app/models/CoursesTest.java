package com.group14.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CoursesTest {

	@Test
	public void isValidCourse() {
		Courses course = new Courses("CSCI1001", "Software Development", "2020", "Spring Boot", "Summer", 1);
		assertEquals(true, course.isValidCourse()); // Checking for correct course details

		course = new Courses("1234$", "SDC", "2020", "Spring Boot", "Summer", 1);
		assertEquals(false, course.isValidCourse()); // Checking for incorrect course id

		course = new Courses("CSCI1001", "SDC$", "2020", "Spring Boot", "Summer", 1);
		assertEquals(false, course.isValidCourse()); // Checking for incorrect course name

		course = new Courses("CSCI1001", "SDC", "2020$", "Spring Boot", "Summer", 1);
		assertEquals(false, course.isValidCourse()); // Checking for incorrect course year

		course = new Courses("CSCI1001", "SDC", "2020", "Spring$Boot", "Summer", 1);
		assertEquals(false, course.isValidCourse()); // Checking for incorrect course description

		course = new Courses("CSCI1001", "SDC", "2020", "Spring Boot", "Summer$", 1);
		assertEquals(false, course.isValidCourse()); // Checking for incorrect course term

	}

}
