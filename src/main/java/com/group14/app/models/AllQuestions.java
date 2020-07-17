package com.group14.app.models;

import java.sql.Timestamp;
import java.util.Map;

public class AllQuestions {

	private int qid;
	private String instructorId;
	private String title;
	private String text;
	private String type;
	private Timestamp createdDate;
	private Map<String, String> options;
	private String storedAs;
	private String displayText;
	private boolean selected;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getInstructor_id() {
		return instructorId;
	}

	public void setInstructor_id(String instructor_id) {
		this.instructorId = instructor_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp timestamp) {
		this.createdDate = timestamp;
	}

	public String getStoredAs() {
		return storedAs;
	}

	public void setStoredAs(String storedAs) {
		this.storedAs = storedAs;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "AllQuestions [qid=" + qid + ", instructor_id=" + instructorId + ", title=" + title + ", text=" + text
				+ ", type=" + type + ", createdDate=" + createdDate + ", options=" + options + ", storedAs=" + storedAs
				+ ", displayText=" + displayText + "]";

	}
}
