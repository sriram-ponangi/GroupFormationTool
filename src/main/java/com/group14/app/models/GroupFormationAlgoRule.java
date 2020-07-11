package com.group14.app.models;

public class GroupFormationAlgoRule {
	private int ruleId;
	private String name;
	private String questionType;
	private String description;

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "GroupFormationAlgoRule [ruleId=" + ruleId + ", name=" + name + ", questionType=" + questionType
				+ ", description=" + description + "]";
	}

}
