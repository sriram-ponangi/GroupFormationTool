package com.group14.app.models;

public class Student {

	private String bannerId;
	private String firstName;
	private String lastName;

	public Student(String bannerID) {
		super();
		this.bannerId = bannerID;
	}

	public Student() {

	}

	public Student(String bannerId, String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.bannerId = bannerId;
		this.lastName = lastName;
	}

	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
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

	public Boolean isValidStudent() {
		return isValidBannerId() && isValidName() ? true : false;

	}

	private Boolean isValidBannerId() {
		if (this.getBannerId() != null && this.getBannerId().length() > 0)
			return this.getBannerId().matches("B00\\d{6}");
		return false;
	}

	private Boolean isValidName() {
		if (this.getFirstName() != null && this.getFirstName().length() > 0 && this.getFirstName().length() < 50
				&& this.getLastName() != null && this.getLastName().length() > 0 && this.getLastName().length() < 50)
			return this.getFirstName().matches("[a-zA-Z ]+") && this.getLastName().matches("[a-zA-Z ]+");
		return false;
	}

}
