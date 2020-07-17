package com.group14.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.group14.app.models.AppUser;
import com.group14.app.models.Email;
import com.group14.app.repositories.IStudentEnrollmentRepository;

@Service
public class StudentEnrollmentService implements IStudentEnrollmentService {

	private static final Logger LOG = LoggerFactory.getLogger(StudentEnrollmentService.class);

	private IStudentEnrollmentRepository studentEnrollmentRepository;

	private IParseUploadedFile<AppUser> parseCSV;

	private IEmailSenderService emailSenderService;

	public StudentEnrollmentService(IStudentEnrollmentRepository studentEnrollmentRepository,
			IParseUploadedFile<AppUser> parseCSV, IEmailSenderService emailSenderService) {
		this.studentEnrollmentRepository = studentEnrollmentRepository;
		this.parseCSV = parseCSV;
		this.emailSenderService = emailSenderService;
	}

	@Override
	public List<AppUser> enrollStudentsToCourseFromFile(MultipartFile studentsListFile, String courseId)
			throws SQLException {
		List<AppUser> studentsList = parseCSV.parseFile(studentsListFile);
		List<AppUser> validUsersList = new ArrayList<>();
		List<AppUser> invalidUsersList = new ArrayList<>();
		for (AppUser user : studentsList) {
			if (user.isValidUser()) {
				validUsersList.add(user);
			} else {
				invalidUsersList.add(user);
			}
		}

		for (AppUser user : validUsersList) {
			LOG.info("Enrolling " + user.getUserId() + " as STUDENT to " + courseId);
			studentEnrollmentRepository.enrollStudentToCourse(user, courseId);
			emailSenderService.sendEmail(new Email("Registered for New Course", user.getEmail(), "Username: "
					+ user.getUserId() + " \nPassword: " + user.getPassword() + " \nNew Course Enrolled: " + courseId));
		}

		return invalidUsersList;
	}

}
