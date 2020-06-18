package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.group14.app.models.RegUser;

class UserRepositoryTests {

	private UserRepository ur = mock(UserRepository.class);

	@Test
	void addUserTest() {

		RegUser user = new RegUser();
		ArrayList<RegUser> list = new ArrayList<>();

		user.setBannerNo("B00100001");
		user.setFirstName("User");
		user.setLastName("One");
		user.setEmail("User.One@dal.ca");
		user.setPassword("password");
		list.add(user);
		list.clear();

		assertFalse(this.ur.addUser(user.getBannerNo(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPassword()), "Success");

	}

}
