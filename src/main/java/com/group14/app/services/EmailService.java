package com.group14.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import java.sql.*;  
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.group14.app.models.Forgotpassword;
import com.group14.app.repositories.ForgotPasswordRepository;


@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	ForgotPasswordRepository fPR = new ForgotPasswordRepository();
	
	String pass;
	String email;
	
	
	public EmailService(JavaMailSender javaMailSender)
	{
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMail(Forgotpassword forgotpassword) throws MailException
	{
		SimpleMailMessage mail = new SimpleMailMessage();
		email = fPR.readEmail(forgotpassword.getBanner().toString());
		mail.setTo(email);
		mail.setFrom("group14sdc@gmail.com");
		mail.setSubject("Forgot Password Link");
		
		pass = fPR.readPass(forgotpassword.getBanner().toString());
		
		mail.setText("Your password is "+pass);
		
		javaMailSender.send(mail);
		fPR.closeConnection();
	}
}
