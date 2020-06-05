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

	
	public List<AppUser> enrollStudentsToCourse(MultipartFile file, String courseId) throws IOException{
		byte[] bytes = file.getBytes();
		SimpleMailMessage mail = new SimpleMailMessage();
        String completeData = new String(bytes);	            
        String[] rows = completeData.split("\\r?\\n");
        List<AppUser> validUsersList = new ArrayList<>();
        List<AppUser> invalidUsersList = new ArrayList<>();
        for(int i=0; i< rows.length; i++) {
        	String[] columns = rows[i].split(",");        	
            AppUser user = new AppUser(columns[0], columns[1], columns[2], columns[3], courseId);
            if(i==0 && !user.isValidUser())
            	continue;
            else if (user.isValidUser()) 
            	validUsersList.add(user);		            
            else 
            	invalidUsersList.add(user);
        }
       
        for (AppUser user: validUsersList) {			
        	LOG.info("Enrolling "+user.getUserId()+" as STUDENT to "+courseId);
    		// Step -1: Users Table
    		// Step -2: SystemRoleMapping with GUEST
    		// STEP -3: CourseRoleMapping with CourseId + STUDENT
        	appUserRepository.enrollStudentToCourse(user,courseId);        			
    	
			// Send Email saying that the user is enrolled with username, password and courseId 
        	
        	mail.setTo(user.getEmail());
    		mail.setFrom("group14sdc@gmail.com");
    		mail.setSubject("Registered for New Course");
    		
    		mail.setText("Username: "+user.getUserId()+" \nPassword: "+user.getPassword()+" \nNew Course Enrolled: "+courseId);
        	javaMailSender.send(mail);
        }
        	
        	
       
		return invalidUsersList;
	}
}
