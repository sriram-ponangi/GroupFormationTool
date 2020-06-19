package com.group14.app.models;

public class PasswordValidatorRules {
	private String ruleId;
	private String regEx;
	private String description;
	private Integer enabled = 1;
	private Integer minMatchCount;
	private Integer maxMatchCount;

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRegEx() {
		return regEx;
	}

	public void setRegEx(String regEx) {
		this.regEx = regEx;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getMinMatchCount() {
		return minMatchCount;
	}

	public void setMinMatchCount(Integer minMatchCount) {
		this.minMatchCount = minMatchCount;
	}

	public Integer getMaxMatchCount() {
		return maxMatchCount;
	}

	public void setMaxMatchCount(Integer maxMatchCount) {
		this.maxMatchCount = maxMatchCount;
	}

	@Override
	public String toString() {
		return "PasswordValidatorRules [ruleId=" + ruleId + ", regEx=" + regEx + ", enabled=" + enabled
				+ ", minMatchCount=" + minMatchCount + ", maxMatchCount=" + maxMatchCount + "]";
	}

}
