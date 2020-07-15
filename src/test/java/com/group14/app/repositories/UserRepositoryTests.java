package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.group14.app.models.AppUser;

class UserRepositoryTests {

	private UserRepository ur = mock(UserRepository.class);

	@Test
	void addUserTest() throws SQLException {

		AppUser user = new AppUser();
		ArrayList<AppUser> list = new ArrayList<>();

		user.setUserId("B00100001");
		user.setFirstName("User");
		user.setLastName("One");
		user.setEmail("User.One@dal.ca");
		user.setPassword("password");
		list.add(user);
		list.clear();

		assertFalse(this.ur.addUser(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPassword()), "Success");

	}

}
