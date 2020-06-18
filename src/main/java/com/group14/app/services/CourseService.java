package com.group14.app.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group14.app.models.Courses;
import com.group14.app.repositories.CourseRepository;
import com.group14.app.repositories.ICourseRepository;

@Service
public class CourseService implements ICourseService {

	private ICourseRepository courseRepository;

	public CourseService(ICourseRepository courseRepository) {
		this.courseRepository = courseRepository;

	}

	@Override
	public void addCourse(Courses courses) {
		try {
			courseRepository.addCourse(courses);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void deleteCourse(Courses courses) {
		try {
			courseRepository.deleteCourse(courses);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public List<Courses> list() throws SQLException {
		try {
			return courseRepository.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
