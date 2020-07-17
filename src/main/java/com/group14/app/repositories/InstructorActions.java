package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group14.app.models.AppUser;
import com.group14.app.models.SQLInput;
import com.group14.app.services.IInstructorActionsService;
import com.group14.app.utils.CRUDRepository;
import com.group14.app.utils.MySQLDBOperations;

@Repository
public class InstructorActions implements IInstructorActionsRepository {

	private IAppUserRepository appUserRepo;

	private CRUDRepository<SQLInput> db;

	private static final Logger LOG = LoggerFactory.getLogger(AppUserRepository.class);

	public InstructorActions(IAppUserRepository appUserRepo, CRUDRepository<SQLInput> db) {
		this.appUserRepo = appUserRepo;
		this.db = db;
	}

	@Override
	public AppUser AddStudentToTAList(String courseId, String bannerId) throws SQLException {
		AppUser appUser = new AppUser();
		appUser = appUserRepo.findByUserName(bannerId);
		LOG.info("AppUser is found");
		return appUser;
	}

	@Override
	public int GiveTaPermission(String bannerId, String role, String courseId) throws SQLException {
		String query = "Update CourseRoleMapper set role_id= ? WHERE user_id= ? AND course_id=?";
		List<Object> params = new ArrayList<>();
		params.add(role);
		params.add(bannerId);
		params.add(courseId);

		System.out.println(params);

		int rowsUpdated = db.save(new SQLInput(query, params));
		LOG.info("Database Updated");
		return rowsUpdated;
	}
}
