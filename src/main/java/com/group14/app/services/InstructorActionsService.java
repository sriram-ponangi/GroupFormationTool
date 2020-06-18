package com.group14.app.services;

import org.springframework.stereotype.Service;

import com.group14.app.models.AppUser;
import com.group14.app.repositories.IInstructorActionsRepository;

@Service
public class InstructorActionsService implements IInstructorActionsService {

	private IInstructorActionsRepository instructorActionsRepository;

	public InstructorActionsService(IInstructorActionsRepository instructorActionsRepository) {
		this.instructorActionsRepository = instructorActionsRepository;
	}

	@Override
	public AppUser AddStudentToTAList(String courseId, String bannerId) {

		AppUser appUser = instructorActionsRepository.AddStudentToTAList(courseId, bannerId);

		return appUser;
	}

	@Override
	public int GiveTaPermission(String bannerId, String courseId) {

		int count = instructorActionsRepository.GiveTaPermission(bannerId, "TA", courseId);
		return count;
	}

}
