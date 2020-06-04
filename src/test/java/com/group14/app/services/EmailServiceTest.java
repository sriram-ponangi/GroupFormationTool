package com.group14.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mail.javamail.JavaMailSender;

import com.group14.app.models.Forgotpassword;
import com.group14.app.repositories.ForgotPasswordRepository;


public class EmailServiceTest {
	
	@Mock
	ForgotPasswordRepository fPR;
	
	@Mock
	private JavaMailSender javaMailSender;
	
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@InjectMocks
	EmailService eS;
	
	@Test
	public void sendMailTest()
	{
		when(fPR.readEmail(Mockito.anyString())).thenReturn("sd240036@dal.ca");
		when(fPR.readPass(Mockito.anyString())).thenReturn("xyzpassword");
		
		System.out.println(fPR);
//		Forgotpassword fP = new Forgotpassword(); 
//		fP.setBanner("B00100002");
		System.out.println(eS);
		eS.sendMail(Mockito.any(Forgotpassword.class));
		
		
		verify(fPR.readEmail(Mockito.anyString()));
		verify(fPR.readPass(Mockito.anyString()));
		
//		verify(javaMailSender.send(Mockito.any(SimpleMailMessage.class),times(1)));
//		assertNotNull(fP.getEmail());
//		assertNotNull(fP.getPassword());
//		assertEquals("sd240036@dal.ca", fP.getEmail());
//		assertEquals("xyzpassword",fP.getPassword());
	}
	
//	@Test
//	public void sendPass()
//	{
//		String mail = fPR.readPass("B00100002");
//		
//		assertNotNull(mail);
//		assertEquals("password", mail);
//	}

}
