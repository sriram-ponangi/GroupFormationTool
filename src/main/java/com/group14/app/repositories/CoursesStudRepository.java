package com.group14.app.repositories;


	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Repository;

	import com.group14.app.models.AppUser;
	import com.group14.app.models.Course;
	import com.group14.app.models.SQLInput;
	import com.group14.app.utils.MySQLDBOperations;

	@Repository
	public class CoursesStudRepository {
		
		@Autowired
		private MySQLDBOperations db;
		
		private static final Logger LOG = LoggerFactory.getLogger(CourseRepository.class);
			
		public ArrayList<Course> getAllCourse(String id) {
			final ArrayList<Course> courseList=new ArrayList<Course>();
			
			List<String> params = new ArrayList<>();
			params.add(id);
			final String SQL = "select * from Courses where course_id!=?;";
			List<HashMap<String,Object>> courseData = db.readData(new SQLInput( SQL, params));
			System.out.println(courseData);
			if(courseData!=null) {
				
				courseData.stream().forEach(row -> {
					Course course = new Course();
					course.setcourseId((String) row.get("course_id"));
					course.setcourseName((String) row.get("name"));
					course.setYear((String) row.get("year"));
					course.setTerm((String) row.get("term"));
					course.setDescription((String) row.get("description"));
					courseList.add(course);
					
					
				});
			}
			System.out.println(courseList);
			
			return courseList;
		}
		public ArrayList<Course> getAssignedCourse(String id) {
			final ArrayList<Course> courseList=new ArrayList<Course>();
			
			List<String> params = new ArrayList<>();
			params.add(id);
			final String SQL = "select c.course_id,name,year,term,description from Courses as c,CourseRoleMapper as cm where c.course_id=cm.course_id and user_id=?;";
			List<HashMap<String,Object>> courseData = db.readData(new SQLInput( SQL, params));
			System.out.println(courseData);
			if(courseData!=null) {
				
				courseData.stream().forEach(row -> {
					Course course = new Course();
					course.setcourseId((String) row.get("course_id"));
					course.setcourseName((String) row.get("name"));
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
