package com.group14.app.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.group14.app.models.Courses;


public class CourseRepository {
	
	static Connection con = null;
	
	static
	{
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		con=DriverManager.getConnection(  
		"jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_14_DEVINT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","CSCI5308_14_DEVINT_USER",
		"CSCI5308_14_DEVINT_14103");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void closeConnection()
	{
			try
			{
				con.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			} 
		
	}
	
	
	public List<Courses> list() throws SQLException {
		List<Courses> rows = new ArrayList<Courses>();
		PreparedStatement stmt=con.prepareStatement("select * from Courses"); 
	    ResultSet resultSet = null;

	        resultSet=stmt.executeQuery();
	        
	        while (resultSet.next()) 
	        {
	            Courses row = new Courses();
	            row.setCid(resultSet.getString("course_id"));
	            row.setName(resultSet.getString("name"));
	            row.setDescription(resultSet.getString("description"));
	            row.setTerm(resultSet.getString("term"));
	            row.setYear(resultSet.getString("year"));
	            row.setEnable(resultSet.getInt("enabled"));
	            
	            rows.add(row);
	            
	        }

	    return rows;
	}
	
	public void addCourse(Courses courses) throws SQLException {
		//List<Courses> courseList = course;
		PreparedStatement stmt=con.prepareStatement("insert into Courses(course_id,name,year,term,description,enabled)"
		+"values (?,?,?,?,?,?)"); 
	    
	    stmt.setString(1, courses.getCid());
	    stmt.setString(2, courses.getName());
	    stmt.setString(3, courses.getYear());
	    stmt.setString(4, courses.getTerm());
	    stmt.setString(5, courses.getDescription());
	    stmt.setInt(6, 1);

	    stmt.execute();
	    
	}
	
	public void deleteCourse(Courses courses) throws SQLException {
		//List<Courses> courseList = course;
		PreparedStatement stmt=con.prepareStatement("delete from Courses where course_id=?"); 
	    
	    stmt.setString(1, courses.getCid());

	    stmt.executeUpdate();
	   
	}

}
