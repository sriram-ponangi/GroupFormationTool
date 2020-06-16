package com.group14.app.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.group14.app.repositories.AppUserRepository;
import com.group14.app.models.AppUser;

@Service
public class CourseAdminService {
	private static final Logger LOG = LoggerFactory.getLogger(CourseAdminService.class);
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;

	
	
}
