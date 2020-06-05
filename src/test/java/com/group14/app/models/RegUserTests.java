package com.group14.app.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegUserTests {

	@Test
	void setBannerNoTest() {
		RegUser g=new RegUser();
		g.setBannerNo("B00812343");
		assertTrue(g.bannerNo.equals("B00812343"));
	}
	
	@Test
	void getBannerNoTest() {
		RegUser g=new RegUser();
		g.setBannerNo("B00812343");
		assertTrue(g.bannerNo.equals("B00812343"));
	}

	@Test
	void setFirstNameTest() {
		RegUser g=new RegUser();
		g.setFirstName("Bella");
		assertTrue(g.firstName.equals("Bella"));
	}
	
	@Test
	void getFirstNameTest() {
		RegUser g=new RegUser();
		g.setFirstName("Bella");
		assertTrue(g.firstName.equals("Bella"));
	}
	
	@Test
	void setLastNameTest() {
		RegUser g=new RegUser();
		g.setLastName("Swan");
		assertTrue(g.lastName.equals("Swan"));
	}
	
	@Test
	void getLastNameTest() {
		RegUser g=new RegUser();
		g.setLastName("Swan");
		assertTrue(g.lastName.equals("Swan"));
	}
	
	@Test
	void setEmailTest() {
		RegUser g=new RegUser();
		g.setEmail("Bella.Swan@dal.ca");
		assertTrue(g.email.equals("Bella.Swan@dal.ca"));
	}
	
	@Test
	void getEmailTest() {
		RegUser g=new RegUser();
		g.setEmail("Bella.Swan@dal.ca");
		assertTrue(g.email.equals("Bella.Swan@dal.ca"));
	}

	@Test
	void setPasswordTest() {
		RegUser g=new RegUser();
		g.setPassword("1234");
		assertTrue(g.password.equals("1234"));
	}
	
	@Test
	void getPasswordTest() {
		RegUser g=new RegUser();
		g.setPassword("1234");
		assertTrue(g.password.equals("1234"));
	}

}
