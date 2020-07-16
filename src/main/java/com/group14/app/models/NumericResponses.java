package com.group14.app.models;

public class NumericResponses {
	private int responseId;
	private String userId;
	private String numanswer;
	private String qid;

	public int getResponseId() {
		return responseId;
	}

	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNumanswer() {
		return numanswer;
	}

	public void setNumanswer(String numanswer) {
		this.numanswer = numanswer;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	@Override
	public String toString() {
		return "NumericResponses [responseId=" + responseId + ", userId=" + userId + ", numanswer=" + numanswer + "]";
	}

}
