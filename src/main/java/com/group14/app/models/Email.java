package com.group14.app.models;

public class Email {
	private String subject;
	private String toEmailAddress;
	private String message;

	public Email(String subject, String toEmailAddress, String message) {
		super();
		this.subject = subject;
		this.toEmailAddress = toEmailAddress;
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToEmailAddress() {
		return toEmailAddress;
	}

	public void setToEmailAddress(String toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}

}
