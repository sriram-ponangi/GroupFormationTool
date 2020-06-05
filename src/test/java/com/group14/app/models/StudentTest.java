package com.group14.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StudentTest {

	@Test
	public void isValidStudentTest() {
		
		Student student = new Student("123456789", "firstName", "lastName");
		assertEquals(false, student.isValidStudent());// Testing for invalid bannerID - Must be starting with B00 and have 6 digits after	
		
		student = new Student("B00100001", "firstName", "lastName");
		assertEquals(true, student.isValidStudent());// Testing for valid bannerID
		
		student = new Student("B00100001", "first-Name", "lastName");
		assertEquals(false, student.isValidStudent());// Testing for invalid firstName - must not have any special characters
		
		student = new Student("B00100001", "first9Name", "lastName");
		assertEquals(false, student.isValidStudent());// Testing for invalid firstName - must not have any digits
		
		student = new Student("B00100001", "first Name", "lastName");
		assertEquals(true, student.isValidStudent());// Testing for valid firstName - can have any spaces
		
		student = new Student("B00100001", "firstName", "last-Name");
		assertEquals(false, student.isValidStudent());// Testing for invalid lastName  - must not have any special characters
		
		student = new Student("B00100001", "firstName", "last9Name");
		assertEquals(false, student.isValidStudent());// Testing for invalid lastName - must not have any digits
		
		student = new Student("B00100001", "firstName", "last Name");
		assertEquals(true, student.isValidStudent());// Testing for valid lastName - can have any spaces
	}
}
