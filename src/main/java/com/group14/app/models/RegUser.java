package com.group14.app.models;

//import javax.persistence.Column;
//import javax.persistence.Entity;

public class RegUser {

  public String bannerNo;
  public String firstName;
  public String lastName;
  public String email;
  public String password;
  


public String getBannerNo() {
	return bannerNo;
}

public void setBannerNo(String bannerNo) {
	this.bannerNo = bannerNo;
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

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

}