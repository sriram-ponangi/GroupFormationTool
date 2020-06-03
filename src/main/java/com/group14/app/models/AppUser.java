package com.group14.app.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AppUser {
	
	private String userId;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String systemRole;
	private Map<String, String> courseRoles;
	private Integer enabled;
	
	public AppUser() {}
	
	public AppUser(String userId, String firstName, String lastName, String email, String courseId) {
		this.userId = userId;
		this.password = getRandomPassword();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;		
		this.systemRole = "GUEST";
		
		Map<String, String> courseRoles = new HashMap<>();
		courseRoles.put(courseId, "STUDENT");
		this.courseRoles = courseRoles;
		
		this.enabled = 1;		
	}	

	public AppUser(String userId, String password, String email, String firstName, String lastName, Integer enabled) {		
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
	
	public static String getRandomPassword() {
			
			int lowerLimit = 48; //  ASCII for '0'
		    int upperLimit = 122; // ASCII for  'z'
		    int targetStringLength = 10;
		    Random random = new Random();
		 
		    return random.ints(lowerLimit, upperLimit + 1)
		      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) // removing special characters
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();
	
	}
	
	public Boolean isValidUser() {
		return isValidUserId() &&
			   isValidName() &&   
			   isValidEmail() 
			   ? true : false;
		
	}
	private Boolean isValidUserId() {
		if (this.getUserId()!=null && this.getUserId().length() > 0) 
			return this.getUserId().matches("B00\\d{6}");
		return false;
	}
	private Boolean isValidName() {
		if (this.getFirstName() !=null && this.getFirstName().length() > 0 && this.getFirstName().length() < 50 &&
				this.getLastName() !=null && this.getLastName().length() > 0 && this.getLastName().length() < 50) 			
			return this.getFirstName().matches("[a-zA-Z ]+") && this.getLastName().matches("[a-zA-Z ]+");
		return false;
	}
	private Boolean isValidEmail() {
		if (this.getEmail()!=null && this.getEmail().length() > 0 && this.getEmail().length() < 50)
			return this.getEmail().matches("[\\w\\.]+@dal.ca");			
		return false;
	}
}
