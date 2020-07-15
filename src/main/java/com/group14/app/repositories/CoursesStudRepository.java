package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.group14.app.models.Courses;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

@Repository
public class CoursesStudRepository implements ICourseStudRepository {

	private CRUDRepository<SQLInput> db;

	public CoursesStudRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	public ArrayList<Courses> getAllCourse(String id) throws SQLException {
		final ArrayList<Courses> courseList = new ArrayList<Courses>();

		List<Object> params = new ArrayList<>();
		params.add(id);
		final String SQL = "select * from Courses where course_id!=?;";
		List<HashMap<String, Object>> courseData = db.readData(new SQLInput(SQL, params));
		System.out.println(courseData);
		if (courseData != null) {

			courseData.stream().forEach(row -> {
				Courses course = new Courses();
				course.setCid((String) row.get("course_id"));
				course.setName((String) row.get("name"));
				course.setYear((String) row.get("year"));
				course.setTerm((String) row.get("term"));
				course.setDescription((String) row.get("description"));
				courseList.add(course);

			});
		}
		System.out.println(courseList);

		return courseList;
	}

	public ArrayList<Courses> getAssignedCourse(String id) throws SQLException {
		final ArrayList<Courses> courseList = new ArrayList<Courses>();

		List<Object> params = new ArrayList<>();
		params.add(id);
		final String SQL = "select c.course_id,name,year,term,description from Courses as c,CourseRoleMapper as cm where c.course_id=cm.course_id and user_id=?;";
		List<HashMap<String, Object>> courseData = db.readData(new SQLInput(SQL, params));
		System.out.println(courseData);
		if (courseData != null) {

			courseData.stream().forEach(row -> {
				Courses course = new Courses();
				course.setCid((String) row.get("course_id"));
				course.setName((String) row.get("name"));
				course.setYear((String) row.get("year"));
				course.setTerm((String) row.get("term"));
				course.setDescription((String) row.get("description"));
				courseList.add(course);

			});
		}
		System.out.println(courseList);

		return courseList;
	}
}
