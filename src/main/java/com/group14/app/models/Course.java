package com.group14.app.models;

public class Course {
	private String courseId;
	private String courseName;
	private String year;
	private String term;
	private String description;

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
