package com.group14.app.models;

public class SurveyQuestionMapper {
	private int responseId;
	private int questionId;
	private int surveyId;
	public int getResponseId() {
		return responseId;
	}
	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	@Override
	public String toString() {
		return "SurveyQuestionMapper [responseId=" + responseId + ", questionId=" + questionId + ", surveyId="
				+ surveyId + "]";
	}

}
