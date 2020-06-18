package com.group14.app.models;

public class MCQuestions {

	private AllQuestions details;
	private String storedAs;
	private String displayText;

	public AllQuestions getDetails() {
		return details;
	}

	public void setDetails(AllQuestions details) {
		this.details = details;
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

	@Override
	public String toString() {
		return "MCQuestions [details=" + details + ", storedAs=" + storedAs + ", displayText=" + displayText + "]";
	}

}
