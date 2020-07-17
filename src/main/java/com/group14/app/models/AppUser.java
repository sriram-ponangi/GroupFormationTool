package com.group14.app.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppUser {

	private static final Logger LOG = LoggerFactory.getLogger(AppUser.class);

	private String userId;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String systemRole;
	private Map<String, String> courseRoles;
	private Integer enabled;

	public AppUser() {
	}

	public AppUser(String bannerId, String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.userId = bannerId;
		this.lastName = lastName;
	}

	public AppUser(String bannerID) {
		super();
		this.userId = bannerID;
	}

	public AppUser(String userId, String firstName, String lastName, String email) {
		this.userId = userId;
		this.password = getRandomPassword();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.systemRole = "GUEST";
		this.enabled = 1;
	}

	public AppUser(String userId, String firstName, String lastName, String email, String courseId) {
		this(userId, firstName, lastName, email);
		Map<String, String> courseRoles = new HashMap<>();
		courseRoles.put(courseId, "STUDENT");
		this.courseRoles = courseRoles;
	}

	public AppUser(String userId, String password, String email, String firstName, String lastName, Integer enabled) {
		this(userId, firstName, lastName, email);
		this.password = password;
		this.courseRoles = null;
		this.enabled = enabled;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(String systemRole) {
		this.systemRole = systemRole;
	}

	public Map<String, String> getCourseRoles() {
		return courseRoles;
	}

	public void setCourseRoles(Map<String, String> courseRoles) {
		this.courseRoles = courseRoles;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "AppUser [userId=" + userId + ", password=" + password + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", systemRole=" + systemRole + ", courseRoles=" + courseRoles
				+ ", enabled=" + enabled + "]";
	}

	public static String getRandomPassword() {

		int lowerLimit = 48; // ASCII for '0'
		int upperLimit = 122; // ASCII for 'z'
		int targetStringLength = 10;
		Random random = new Random();

		return random.ints(lowerLimit, upperLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) // removing
																													// special
																													// characters
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

	}

	public Boolean isValidUser() {
		boolean ans = isValidUserId() && isValidName() && isValidEmail() ? true : false;
		return ans;

	}

	private Boolean isValidUserId() {
		if (this.getUserId() != null && this.getUserId().length() > 0) {
			return this.getUserId().matches("B00\\d{6}");
		}
		return false;
	}

	private Boolean isValidName() {
		if (this.getFirstName() != null && this.getFirstName().length() > 0 && this.getFirstName().length() < 50
				&& this.getLastName() != null && this.getLastName().length() > 0 && this.getLastName().length() < 50) {
			return this.getFirstName().matches("[a-zA-Z ]+") && this.getLastName().matches("[a-zA-Z ]+");
		}
		return false;
	}

	private Boolean isValidEmail() {
		if (this.getEmail() != null && this.getEmail().length() > 0 && this.getEmail().length() < 50) {
			return this.getEmail().matches("[\\w\\.]+@dal.ca");
		}
		return false;
	}

	public static AppUser getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserPrincipal userPrincipal = (UserPrincipal) principal;
		return userPrincipal.getUser();
	}

	// Check if Principal/logged-in user has TA or INSTRUCTOR ROLE for the given
	// courseId
	public static boolean hasInstructorOrTARoleForCourse(String courseId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserPrincipal userPrincipal = (UserPrincipal) principal;
		String courseRole = userPrincipal.getUser().getCourseRoles().get(courseId);
		if (userPrincipal.getUser().getSystemRole().equalsIgnoreCase("ADMIN")) {
			return true;
		} else if (courseRole != null
				&& (courseRole.equalsIgnoreCase("TA") || courseRole.equalsIgnoreCase("INSTRUCTOR"))) {
			LOG.info(userPrincipal.getUser().getUserId() + " is accessing course admin feature with " + courseRole
					+ " ROLE for the course " + courseId);
			return true;
		} else {
			LOG.info(userPrincipal.getUser().getUserId()
					+ " is accessing course admin feature with no ROLE for the course " + courseId);
			return false;
		}
	}

	public Boolean isValidStudent() {
		return isValidBannerId() && isValidName() ? true : false;

	}

	private Boolean isValidBannerId() {
		if (this.getUserId() != null && this.getUserId().length() > 0)
			return this.getUserId().matches("B00\\d{6}");
		return false;
	}

}
