package com.group14.app.exceptions;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.group14.app.controllers.SurveyController;

// Rob allowed us to use these annotations
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(SQLException.class)
	public String sqlExceptionHandler(SQLException e, Model model) {
		String errorMessage = "Reason: There has been some unexpected issue with our database";
		model.addAttribute("errorMessage", errorMessage);
		LOG.error(e.getMessage());
		e.printStackTrace();
		return "GlobalExceptionHandlerPage";
	}

	@ExceptionHandler(MailException.class)
	public String emailExceptionHandler(MailException e, Model model) {
		String errorMessage = "Reason: There has been some unexpected issue while sending an email";
		model.addAttribute("errorMessage", errorMessage);
		LOG.error(e.getMessage());
		e.printStackTrace();
		return "GlobalExceptionHandlerPage";
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public String unknownUserExceptionHandler(UsernameNotFoundException e, Model model) {
		String errorMessage = "Reason: The user credentials might not be correct.";
		model.addAttribute("errorMessage", errorMessage);
		LOG.error(e.getMessage());
		e.printStackTrace();
		return "GlobalExceptionHandlerPage";
	}

	@ExceptionHandler(Exception.class)
	public String anyExceptionHandler(Exception e, Model model) {
		String errorMessage = "Reason: There has been some unexpected issue with our systems.";
		model.addAttribute("errorMessage", errorMessage);
		LOG.error(e.getMessage());
		e.printStackTrace();
		return "GlobalExceptionHandlerPage";
	}
}
