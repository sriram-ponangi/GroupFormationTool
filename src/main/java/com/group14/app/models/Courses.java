package com.group14.app.models;

public class Courses {

	private String cid;
	private String name;
	private String year;
	private String description;
	private String term;
	private int enable;

	public Courses(String cid, String name, String year, String description, String term, int enable) {
		this.cid = cid;
		this.name = name;
		this.year = year;
		this.description = description;
		this.term = term;
		this.enable = enable;
	}

	public Courses() {

	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	private Boolean isValidCourseDetails() {
		if (this.getCid() != null && this.getCid().matches("[a-zA-Z0-9]+") && this.getName() != null
				&& this.getName().matches("[a-zA-Z ]+") && this.getTerm() != null && this.getTerm().matches("[a-zA-Z]+")
				&& this.getYear() != null && this.getYear().matches("[0-9]+") && this.getDescription() != null
				&& this.getDescription().matches("[a-zA-Z ]+")) {
			return true;
		} else {
			return false;
		}

	}

	public Boolean isValidCourse() {
		return isValidCourseDetails() ? true : false;
	}

}
