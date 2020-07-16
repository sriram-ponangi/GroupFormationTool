package com.group14.app.models;

public class TextResponses {
	private int responseId;
	private String userId;
	private String textanswer;
	private String tqid;

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

	public String getTextanswer() {
		return textanswer;
	}

	public void setTextanswer(String textanswer) {
		this.textanswer = textanswer;
	}

	public String getTqid() {
		return tqid;
	}

	public void setTqid(String tqid) {
		this.tqid = tqid;
	}

	@Override
	public String toString() {
		return "TextResponses [responseId=" + responseId + ", userId=" + userId + ", textanswer=" + textanswer + "]";
	}

}
