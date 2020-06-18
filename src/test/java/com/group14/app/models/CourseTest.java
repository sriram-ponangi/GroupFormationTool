package com.group14.app.models;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CourseTest {

	@Test
	void setCourseIdTest() {
		Course c = new Course();
		c.setcourseId("CSCI1001");
		assertTrue(c.getcourseId().equals("CSCI1001"));
	}

	@Test
	void getCourseIdTest() {
		Course c = new Course();
		c.setcourseId("CSCI1001");
		assertTrue(c.getcourseId().equals("CSCI1001"));
	}

	@Test
	void setCourseNameTest() {
		Course c = new Course();
		c.setcourseName("Course One");
		assertTrue(c.getcourseName().equals("Course One"));
	}

	@Test
	void getCourseNameTest() {
		Course c = new Course();
		c.setcourseName("Course One");
		assertTrue(c.getcourseName().equals("Course One"));
	}

	@Test
	void setYearTest() {
		Course c = new Course();
		c.setYear("2020");
		assertTrue(c.getYear().equals("2020"));
	}

	@Test
	void getYearTest() {
		Course c = new Course();
		c.setYear("2020");
		assertTrue(c.getYear().equals("2020"));
	}

	@Test
	void setTermTest() {
		Course c = new Course();
		c.setTerm("Fall");
		assertTrue(c.getTerm().equals("Fall"));
	}

	@Test
	void getTermTest() {
		Course c = new Course();
		c.setTerm("Fall");
		assertTrue(c.getTerm().equals("Fall"));
	}

	@Test
	void setDescriptionTest() {
		Course c = new Course();
		c.setDescription("Course One Info");
		assertTrue(c.getDescription().equals("Course One Info"));
	}

	@Test
	void getDescriptionTest() {
		Course c = new Course();
		c.setDescription("Course One Info");
		assertTrue(c.getDescription().equals("Course One Info"));

	}

}
