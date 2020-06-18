package com.group14.app.models;

import java.sql.Timestamp;

public class AllQuestions {

	private int qid;
	private String instructor_id;
	private String title;
	private String text;
	private String type;
	public Timestamp createdDate;

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getInstructor_id() {
		return instructor_id;
	}

	public void setInstructor_id(String instructor_id) {
		this.instructor_id = instructor_id;
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

}
