package com.group14.app.models;

public class Survey {
	private int surveyId;
	private int published;
	private int groupSize;
	private String courseId;

	public Survey(int surveyId, int published, int groupSize, String courseId) {
		this.courseId = courseId;
		this.groupSize = groupSize;
		this.published = published;
		this.surveyId = surveyId;
	}

	public Survey() {

	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public int getPublished() {
		return published;
	}

	public void setPublished(int published) {
		this.published = published;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", published=" + published + ", groupSize=" + groupSize + ", courseId="
				+ courseId + "]";
	}

	private Boolean isValidSurveyDetails() {
		if (this.getCourseId() != null && this.getCourseId().matches("CSCI\\d{4}") && this.getCourseId().length() > 0
				&& this.getPublished() >= 0 && this.getGroupSize() > 0 && this.getSurveyId() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isValidSurvey() {
		return isValidSurveyDetails() ? true : false;
	}
}
