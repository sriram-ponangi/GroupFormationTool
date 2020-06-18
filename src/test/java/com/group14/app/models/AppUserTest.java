package com.group14.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AppUserTest {
	@Test
	public void getRandomPasswordTest() {
		String password = AppUser.getRandomPassword();
		assertEquals(10, password.length());
	}

	@Test
	public void isValidUserTest() {

		AppUser user = new AppUser("123456789", "firstName", "lastName", "email@dal.ca", "courseId");
		assertEquals(false, user.isValidUser());// Testing for invalid userID - Must be starting with B00 and have 6
												// digits after
		user = new AppUser("B00100001", "firstName", "lastName", "email@dal.ca", "courseId");
		assertEquals(true, user.isValidUser());// Testing for valid userID

		user = new AppUser("B00100001", "first-Name", "lastName", "email@dal.ca", "courseId");
		assertEquals(false, user.isValidUser());// Testing for invalid firstName - must not have any special characters
		user = new AppUser("B00100001", "first9Name", "lastName", "email@dal.ca", "courseId");
		assertEquals(false, user.isValidUser());// Testing for invalid firstName - must not have any digits
		user = new AppUser("B00100001", "first Name", "lastName", "email@dal.ca", "courseId");
		assertEquals(true, user.isValidUser());// Testing for valid firstName - can have any spaces

		user = new AppUser("B00100001", "firstName", "last-Name", "email@dal.ca", "courseId");
		assertEquals(false, user.isValidUser());// Testing for invalid lastName - must not have any special characters
		user = new AppUser("B00100001", "firstName", "last9Name", "email@dal.ca", "courseId");
		assertEquals(false, user.isValidUser());// Testing for invalid lastName - must not have any digits
		user = new AppUser("B00100001", "firstName", "last Name", "email@dal.ca", "courseId");
		assertEquals(true, user.isValidUser());// Testing for valid lastName - can have any spaces

		user = new AppUser("B00100001", "firstName", "lastName", "email-?8@dal.ca", "courseId");
		assertEquals(false, user.isValidUser());// Testing for invalid email - must not have any special characters
												// apart from period
		user = new AppUser("B00100001", "firstName", "lastName", "firstName.lastName@dal.ca", "courseId");
		assertEquals(true, user.isValidUser());// Testing for valid email
		user = new AppUser("B00100001", "firstName", "lastName", "firstName99@dal.ca", "courseId");
		assertEquals(true, user.isValidUser());// Testing for valid email
		user = new AppUser("B00100001", "firstName", "lastName", "firstName.lastName@gmail.com", "courseId");
		assertEquals(false, user.isValidUser());// Testing for invalid email - must be @dal.ca
	}
}
