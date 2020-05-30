package com.group14.app.models;

import java.util.Map;

public class AppUser {
	
	private String userId;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String systemRole;
	private Map<String, String> courseRoles;
	private Integer enabled;
	
	
	public AppUser(String userId, String password, String email, String firstName, String lastName, Integer enabled) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.systemRole = "GUEST";
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
	
	
}
