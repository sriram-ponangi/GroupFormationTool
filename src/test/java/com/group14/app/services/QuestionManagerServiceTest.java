package com.group14.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.group14.app.models.AppUser;
import com.group14.app.repositories.QuestionManagerRepository;

public class QuestionManagerServiceTest {

	@InjectMocks
	private QuestionManagerService qMS;

	@Mock
	private QuestionManagerRepository qMR;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getRole() throws SQLException {
		AppUser user = new AppUser();

		user.setUserId("B00100001");
		when(this.qMR.FindRoleForID(1)).thenReturn(user.getUserId());
		boolean result = this.qMS.getRoleFromID(1, "B00100001");
		assertEquals(true, result);// check for matched instructor with question id

		user.setUserId("B00100001");
		when(this.qMR.FindRoleForID(2)).thenReturn(user.getUserId());
		boolean result1 = this.qMS.getRoleFromID(2, "B00100002");
		assertEquals(false, result1);// checks for unmatched instructor with question id
	}

}
