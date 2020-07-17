package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group14.app.models.CourseRoleMapper;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;
import com.group14.app.utils.MySQLDBOperations;

@Repository
public class CourseRoleMapperRepository implements ICourseRoleMapperRepository {

	private CRUDRepository<SQLInput> db;

	public CourseRoleMapperRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	@Override
	public List<CourseRoleMapper> list() throws SQLException {

		String SQL_GET_USER = "SELECT * FROM CourseRoleMapper WHERE role_id = ?";
		List<Object> params = new ArrayList<>();
		params.add("INSTRUCTOR");
		final List<CourseRoleMapper> rows = new ArrayList<CourseRoleMapper>();

		List<HashMap<String, Object>> usersData = db.readData(new SQLInput(SQL_GET_USER, params));

		if (usersData != null)
			usersData.stream().forEach(row -> {
				CourseRoleMapper cRM = new CourseRoleMapper();
				cRM.setRole_id((String) row.get("role_id"));
				cRM.setUser_id((String) row.get("user_id"));
				cRM.setCourse_id((String) row.get("course_id"));
				rows.add(cRM);
			});
		else {
			return null;
		}

		return rows;

	}

	@Override
	public void addCourseI(String banner, String cid) throws SQLException {

		String SQL_GET_USER = "insert into CourseRoleMapper(role_id,user_id,course_id) values (?,?,?)";
		List<Object> params = new ArrayList<>();
		params.add("INSTRUCTOR");
		params.add(banner);
		params.add(cid);

		int usersData = db.save(new SQLInput(SQL_GET_USER, params));

	}

	@Override
	public ArrayList<CourseRoleMapper> getInstructorId(String courseId) throws SQLException {

		String SQL_GET_USER = "SELECT * FROM CourseRoleMapper WHERE course_id = ?";
		List<Object> params = new ArrayList<>();
		params.add(courseId);
		final List<CourseRoleMapper> rows = new ArrayList<CourseRoleMapper>();

		List<HashMap<String, Object>> usersData = db.readData(new SQLInput(SQL_GET_USER, params));

		if (usersData != null)
			usersData.stream().forEach(row -> {
				CourseRoleMapper cRM = new CourseRoleMapper();
				cRM.setRole_id((String) row.get("role_id"));
				cRM.setUser_id((String) row.get("user_id"));
				cRM.setCourse_id((String) row.get("course_id"));
				rows.add(cRM);
			});
		else {
			return null;
		}

		return (ArrayList<CourseRoleMapper>) rows;
	}
}
