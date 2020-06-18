package com.group14.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.group14.app.models.Email;

@Service
public class EmailSenderService implements IEmailSenderService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public boolean sendEmail(Email info) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(info.getToEmailAddress());
		mail.setFrom(IEmailSenderService.FROM);
		mail.setSubject(info.getSubject());
		mail.setText(info.getMessage());
		javaMailSender.send(mail);
		return false;
	}

}
