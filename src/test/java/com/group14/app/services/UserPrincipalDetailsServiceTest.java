package com.group14.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.group14.app.models.AppUser;
import com.group14.app.models.UserPrincipal;
import com.group14.app.repositories.AppUserRepository;

public class UserPrincipalDetailsServiceTest {

	@InjectMocks
	private UserPrincipalDetailsService userPrincipalDetailsService;

	@Mock
	private AppUserRepository appUserRepositoryMock;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void loadUserByUsernameTest_basic() throws SQLException {
		AppUser appUser = new AppUser("userId", "password", "email", "firstName", "lastName", 1);
		appUser.setSystemRole("GUEST");
		Map<String, String> courseRoles = new HashMap<>();
		courseRoles.put("CSCI1001", "STUDENT");
		courseRoles.put("CSCI1002", "TA");
		appUser.setCourseRoles(courseRoles);

		when(appUserRepositoryMock.findByUserName(Mockito.anyString())).thenReturn(appUser);

		UserPrincipal userDetails = (UserPrincipal) userPrincipalDetailsService.loadUserByUsername("B00100001");

		assertEquals("userId", userDetails.getUsername());
		assertEquals("password", userDetails.getPassword());
		assertEquals("email", userDetails.getUser().getEmail());
		assertEquals("firstName", userDetails.getUser().getFirstName());
		assertEquals("lastName", userDetails.getUser().getLastName());
		assertEquals(1, userDetails.getUser().getEnabled());
		assertEquals(true, userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_GUEST")));
		assertEquals(true, userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STUDENT")));
		assertEquals(true, userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TA")));
	}

	@Test
	public void loadUserByUsernameTest_testUsernameNotFoundException() throws SQLException {
		AppUser appUser = null;

		when(appUserRepositoryMock.findByUserName(Mockito.anyString())).thenReturn(appUser);

		Throwable exception = assertThrows(UsernameNotFoundException.class,
				() -> userPrincipalDetailsService.loadUserByUsername("B00100001"));
		assertEquals("Invalid Credentials", exception.getMessage());

	}

}
