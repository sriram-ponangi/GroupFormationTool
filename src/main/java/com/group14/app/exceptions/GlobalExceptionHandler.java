package com.group14.app.exceptions;

import java.sql.SQLException;

import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Rob allowed us to use these annotations
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(SQLException.class)
	public String sqlExceptionHandler(SQLException e, Model model) {
		String errorMessage = "Reason: There has been some unexpected issue with our database";
		model.addAttribute("errorMessage", errorMessage);
		return "GlobalExceptionHandlerPage";
	}
	
	@ExceptionHandler(MailException.class)
	public String emailExceptionHandler(MailException e, Model model) {
		String errorMessage = "Reason: There has been some unexpected issue while sending an email";
		model.addAttribute("errorMessage", errorMessage);
		return "GlobalExceptionHandlerPage";
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public String unknownUserExceptionHandler(UsernameNotFoundException e, Model model) {
		String errorMessage = "Reason: The user credentials might not be correct.";
		model.addAttribute("errorMessage", errorMessage);
		return "GlobalExceptionHandlerPage";
	}
	
	@ExceptionHandler(Exception.class)
	public String anyExceptionHandler(Exception e, Model model) {
		String errorMessage = "Reason: There has been some unexpected issue with our systems.";
		model.addAttribute("errorMessage", errorMessage);
		e.printStackTrace();
		return "GlobalExceptionHandlerPage";
	}
}
