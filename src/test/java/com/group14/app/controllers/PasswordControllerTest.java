package com.group14.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.Model;

import com.group14.app.models.AppUser;
import com.group14.app.models.PasswordValidatorRules;
import com.group14.app.services.IPasswordService;

public class PasswordControllerTest {
	@Spy
	@InjectMocks  	
	PasswordController passwordValidatorController;
		
	@Mock
	Model model;
	
	@Mock
	IPasswordService pvs;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}	
	
	@Test
	public void updatePasswordPageTest() {
		when(model.addAttribute(Mockito.anyString(), Mockito.anyString())).thenReturn(model);
		assertEquals("UpdateUserPassword", passwordValidatorController.updatePasswordPage(model));
	}
	
	@Test
	public void updatePasswordTest_EmptyPasswordErrork() {
		AppUser userPassword = new AppUser();
		assertEquals("UpdatePasswordError", passwordValidatorController.updatePassword(userPassword, model));
		
	}
	
	@Test
	public void updatePasswordTest_FailedPasswordPolicyRulesEqualsNull() {
		AppUser userPassword = new AppUser();
		userPassword.setPassword("Test@123");		
		Mockito.doReturn(null).when(passwordValidatorController).getCurrentUser();
		when(pvs.validatePassword(Mockito.any(), Mockito.anyString())).thenReturn(null);
		when(model.addAttribute(Mockito.anyString(), Mockito.any())).thenReturn(model);		
		assertEquals("UpdatePasswordError", passwordValidatorController.updatePassword(userPassword, model));
		
	}
	
	@Test
	public void updatePasswordTest_ConatinsFailedPasswordPolicyRules() {
		AppUser userPassword = new AppUser();
		userPassword.setPassword("Test@123");		
		List<PasswordValidatorRules> faliedRules = new ArrayList<>();
		faliedRules.add(new PasswordValidatorRules());
		Mockito.doReturn(null).when(passwordValidatorController).getCurrentUser();
		when(pvs.validatePassword(Mockito.any(), Mockito.anyString())).thenReturn(faliedRules);
		when(model.addAttribute(Mockito.anyString(), Mockito.any())).thenReturn(model);		
		assertEquals("UpdatePasswordError", passwordValidatorController.updatePassword(userPassword, model));		
	}
	
	@Test
	public void updatePasswordTest_Success() {
		AppUser user = new AppUser();
		user.setPassword("Test@123");		
		List<PasswordValidatorRules> faliedRules = new ArrayList<>();
		
		Mockito.doReturn(user).when(passwordValidatorController).getCurrentUser();
		when(pvs.validatePassword(Mockito.any(), Mockito.anyString())).thenReturn(faliedRules);
		when(pvs.updatePassword( Mockito.any(), Mockito.any())).thenReturn(true);
		
		when(model.addAttribute(Mockito.anyString(), Mockito.any())).thenReturn(model);		
		assertEquals("UpdatePasswordSuccess", passwordValidatorController.updatePassword(user, model));		
	}
	
	
}
