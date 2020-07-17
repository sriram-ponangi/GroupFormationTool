package com.group14.app.models;

import java.util.ArrayList;
import java.util.List;

public class SurveyAlgorithmInfo {
	private String action;
	private String courseId;
	private int surveyId;
	private int published;
	private List<SurveyRuleMapper> algorithmRules;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
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

	public List<SurveyRuleMapper> getAlgorithmRules() {
		return algorithmRules;
	}

	public void setAlgorithmRules(List<SurveyRuleMapper> algorithmRules) {
		this.algorithmRules = algorithmRules;
	}

	@Override
	public String toString() {
		return "SurveyAlgorithmInfo [action=" + action + ", courseId=" + courseId + ", surveyId=" + surveyId
				+ ", published=" + published + ", algorithmRules=" + algorithmRules + "]";
	}

}
