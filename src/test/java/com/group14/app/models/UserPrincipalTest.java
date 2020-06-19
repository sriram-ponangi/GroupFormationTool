package com.group14.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserPrincipalTest {

	@Test
	public void getAuthoritiesTest_basic() {
		AppUser appUser = new AppUser("userId", "password", "email", "firstName", "lastName", 1);
		appUser.setSystemRole("GUEST");
		Map<String, String> courseRoles = new HashMap<>();
		courseRoles.put("CSCI1001", "STUDENT");
		courseRoles.put("CSCI1002", "TA");
		appUser.setCourseRoles(courseRoles);

		UserPrincipal userPrincipal = new UserPrincipal(appUser);

		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		roles.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
		roles.add(new SimpleGrantedAuthority("ROLE_TA"));

		assertEquals(true, userPrincipal.getAuthorities().containsAll(roles));
	}

}
