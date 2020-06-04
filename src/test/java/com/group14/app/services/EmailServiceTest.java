package com.group14.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.group14.app.repositories.ForgotPasswordRepository;

public class EmailServiceTest {
	
	ForgotPasswordRepository fPR = new ForgotPasswordRepository();
	
	@Test
	public void sendMail()
	{
		String mail = fPR.readEmail("B00100002");
		
		assertNotNull(mail);
		assertEquals("sd240036@dal.ca", mail);
	}
	
	@Test
	public void sendPass()
	{
		String mail = fPR.readPass("B00100002");
		
		assertNotNull(mail);
		assertEquals("password", mail);
	}

}
