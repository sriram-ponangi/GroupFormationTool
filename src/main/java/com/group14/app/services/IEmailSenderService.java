package com.group14.app.services;

import com.group14.app.models.Email;

public interface IEmailSenderService {
	String FROM = "group14sdc@gmail.com";

	boolean sendEmail(Email info);
}
