package com.group14.app.models;

import java.util.ArrayList;

public class Course {
	  public String courseId;
	  public String courseName;
	  public String year;
	  public String term;
	  public String description;

	  	public String getcourseId() {
		return courseId;
	}

	public void setcourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getcourseName() {
		return courseName;
	}

	public void setcourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
