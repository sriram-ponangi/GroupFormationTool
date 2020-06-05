package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.group14.app.models.Courses;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.MySQLDBOperations;

@Repository
public class CourseRepository {
	
	@Autowired
	private MySQLDBOperations db;
	
	
	public List<Courses> list() throws SQLException {

		
		String SQL_GET_COURSES = "select * from Courses";
		List<String> params = new ArrayList<>();
		
		final List<Courses> rows = new ArrayList<Courses>();
		
		List<HashMap<String,Object>> usersData = db.readData(new SQLInput( SQL_GET_COURSES, params));
		
		if(usersData!=null)
			usersData.stream().forEach(row -> {
				Courses course = new Courses();
				course.setCid(((String) row.get("course_id")));
				course.setName(((String) row.get("name")));
				course.setTerm(((String) row.get("term")));
				course.setYear(((String) row.get("year")));
				course.setDescription(((String) row.get("description")));
				course.setEnable(((int) row.get("enabled")));
				rows.add(course);
			});
		else {

			return null;
		}
		return rows;
	}
	
	public void addCourse(Courses courses) throws SQLException {
		
		String SQL_GET_USER = "insert into Courses(course_id,name,year,term,description) values (?,?,?,?,?)";
		List<String> params = new ArrayList<>();
		params.add(courses.getCid());
		params.add(courses.getName());
		params.add(courses.getYear());
		params.add(courses.getTerm());
		params.add(courses.getDescription());
		
		
		int usersData = db.save(new SQLInput( SQL_GET_USER, params));
	    
	}
	
	public void deleteCourse(Courses courses) throws SQLException {

		System.out.println(courses);
		String SQL_DELETE_USER = "delete from CourseRoleMapper where course_id=?";
		String SQL_DELETE_COURSE = "delete from Courses where course_id=?";
		List<String> params = new ArrayList<>();
		params.add(courses.getCid());
		
		int deleteData = db.save(new SQLInput( SQL_DELETE_USER, params));
		int usersData = db.save(new SQLInput( SQL_DELETE_COURSE, params));
		
	   
	}
	
	

}
