package com.group14.app.models;

public class SurveyQuestionMapper {
	private int responseId;
	private int questionId;
	private int surveyId;

	public SurveyQuestionMapper(int responseId, int questionId, int surveyId) {
		this.questionId = questionId;
		this.responseId = responseId;
		this.surveyId = surveyId;
	}

	public SurveyQuestionMapper() {
	}

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

	private Boolean isValidSurveyQuestionMapperDetails() {
		if (this.getResponseId() > 0 && this.getSurveyId() > 0 && this.getQuestionId() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isValidSurveyQuestionMapper() {
		return isValidSurveyQuestionMapperDetails() ? true : false;
	}

}
