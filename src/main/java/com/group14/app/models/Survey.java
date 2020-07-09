package com.group14.app.models;

public class Survey {
	private int surveyId;
	private int published;
	private int groupSize;
	private String courseId;

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

}
