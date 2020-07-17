package com.group14.app.models;

public class SurveyRuleMapper {
	private int responseId;
	private int ruleId;
	private String additionalInfo;

	public int getResponseId() {
		return responseId;
	}

	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	@Override
	public String toString() {
		return "SurveyRuleMapper [responseId=" + responseId + ", ruleId=" + ruleId + ", addtionalInfo=" + additionalInfo
				+ "]";
	}

}
