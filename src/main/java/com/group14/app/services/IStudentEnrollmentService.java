package com.group14.app.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.group14.app.models.AppUser;

public interface IStudentEnrollmentService {
	List<AppUser> enrollStudentsToCourseFromFile(MultipartFile studentsListFile , String courseId);
}
