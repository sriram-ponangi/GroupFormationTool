package com.group14.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.group14.app.models.AppUser;
import com.group14.app.models.PasswordValidatorRules;
import com.group14.app.repositories.IPasswordReposiotry;

public class PasswordServiceTest {

	@InjectMocks
	private PasswordService pvs;

	@Mock
	private IPasswordReposiotry pvr;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void validatePasswordTest_HistoryPolicyFailed() throws SQLException {
		AppUser user = new AppUser();
		user.setUserId("B00100001");
		List<String> previousPasswords = new ArrayList<>();
		previousPasswords.add("Test@111");
		previousPasswords.add("Test@222");
		previousPasswords.add("Test@333");

		when(pvr.getActiveRules()).thenReturn(mockActiveRulesData());
		when(pvr.getPreviousPasswords(Mockito.anyString(), Mockito.anyInt())).thenReturn(previousPasswords);

		List<PasswordValidatorRules> result = this.pvs.validatePassword(user, "Test@111");

		assertEquals("PASSWORD_HISTORY", result.get(0).getRuleId());
	}

	@Test
	public void validatePasswordTest_LengthPolicyFailed() throws SQLException {
		AppUser user = new AppUser();
		user.setUserId("B00100001");
		List<String> previousPasswords = new ArrayList<>();
		previousPasswords.add("Test@111");
		previousPasswords.add("Test@222");
		previousPasswords.add("Test@333");

		when(pvr.getActiveRules()).thenReturn(mockActiveRulesData());
		when(pvr.getPreviousPasswords(Mockito.anyString(), Mockito.anyInt())).thenReturn(previousPasswords);

		List<PasswordValidatorRules> result = this.pvs.validatePassword(user, "Tt@1");

		assertEquals("TotalLength", result.get(0).getRuleId());
	}

	@Test
	public void validatePasswordTest_CasePolicyFailed() throws SQLException {
		AppUser user = new AppUser();
		user.setUserId("B00100001");
		List<String> previousPasswords = new ArrayList<>();
		previousPasswords.add("Test@111");
		previousPasswords.add("Test@222");
		previousPasswords.add("Test@333");

		when(pvr.getActiveRules()).thenReturn(mockActiveRulesData());
		when(pvr.getPreviousPasswords(Mockito.anyString(), Mockito.anyInt())).thenReturn(previousPasswords);

		List<PasswordValidatorRules> result = this.pvs.validatePassword(user, "test@11");
		assertEquals("UpperCaseCharactersLength", result.get(0).getRuleId());

		result = this.pvs.validatePassword(user, "TEST@11");
		assertEquals("LowerCaseCharactersLength", result.get(0).getRuleId());
	}

	@Test
	public void validatePasswordTest_SpecialCharacterPolicyFailed() throws SQLException {
		AppUser user = new AppUser();
		user.setUserId("B00100001");
		List<String> previousPasswords = new ArrayList<>();
		previousPasswords.add("Test@111");
		previousPasswords.add("Test@222");
		previousPasswords.add("Test@333");

		when(pvr.getActiveRules()).thenReturn(mockActiveRulesData());
		when(pvr.getPreviousPasswords(Mockito.anyString(), Mockito.anyInt())).thenReturn(previousPasswords);

		List<PasswordValidatorRules> result = this.pvs.validatePassword(user, "test11");
		assertEquals("SpecialCharactersLength", result.get(0).getRuleId());

	}

	private List<PasswordValidatorRules> mockActiveRulesData() {
		List<PasswordValidatorRules> mockRules = new ArrayList<>();
		PasswordValidatorRules rule = new PasswordValidatorRules();
		rule.setRuleId("LowerCaseCharactersLength");
		rule.setRegEx("[a-z]");
		rule.setDescription("No. of Lower Case characters in the password should be a minimum of 1");
		rule.setMinMatchCount(1);
		rule.setMaxMatchCount(-1);
		rule.setEnabled(1);
		mockRules.add(rule);

		rule = new PasswordValidatorRules();
		rule.setRuleId("PASSWORD_HISTORY");
		rule.setRegEx("");
		rule.setDescription("The password cannot be same as any of your last 3 passwords");
		rule.setMinMatchCount(3);
		rule.setMaxMatchCount(-1);
		rule.setEnabled(1);
		mockRules.add(rule);

		rule = new PasswordValidatorRules();
		rule.setRuleId("SpecialCharactersLength");
		rule.setRegEx("[^A-Za-z0-9]");
		rule.setDescription("No. of Special characters in the password should be a minimum of 1");
		rule.setMinMatchCount(1);
		rule.setMaxMatchCount(-1);
		rule.setEnabled(1);
		mockRules.add(rule);

		rule = new PasswordValidatorRules();
		rule.setRuleId("TotalLength");
		rule.setRegEx("\\S");
		rule.setDescription("Length of the password should be a minimum of 6 and a maximum of 20");
		rule.setMinMatchCount(6);
		rule.setMaxMatchCount(20);
		rule.setEnabled(1);
		mockRules.add(rule);

		rule = new PasswordValidatorRules();
		rule.setRuleId("UpperCaseCharactersLength");
		rule.setRegEx("[A-Z]");
		rule.setDescription("No. of Upper Case characters in the password should be a minimum of 1");
		rule.setMinMatchCount(1);
		rule.setMaxMatchCount(-1);
		rule.setEnabled(1);
		mockRules.add(rule);

		return mockRules;
	}

}
