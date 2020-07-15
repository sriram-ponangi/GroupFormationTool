package com.group14.app.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.group14.app.models.AppUser;
import com.group14.app.repositories.ForgotPasswordRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	ForgotPasswordRepository fPR;

	String pass;
	String email;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public EmailService() {

	}

	public void sendMail(AppUser forgotpassword) throws MailException, SQLException {
		SimpleMailMessage mail = new SimpleMailMessage();
		email = fPR.readEmail(forgotpassword.getUserId().toString());
		mail.setTo(email);
		mail.setFrom("group14sdc@gmail.com");
		mail.setSubject("Forgot Password Link");

		pass = fPR.readPass(forgotpassword.getUserId().toString());

		mail.setText("Your password is " + pass);

		javaMailSender.send(mail);
	}
}
