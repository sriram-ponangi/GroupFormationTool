package com.group14.app.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.group14.app.models.CourseRoleMapper;

public class CourseRoleMapperRepository {

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
	
	
	public List<CourseRoleMapper> list() throws SQLException {
		List<CourseRoleMapper> rows = new ArrayList<CourseRoleMapper>();
		PreparedStatement stmt=con.prepareStatement("select * from CourseRoleMapper where role_id=?"); 
		stmt.setString(1, "INSTRUCTOR");
		ResultSet resultSet = null;

        resultSet=stmt.executeQuery();
        
        while (resultSet.next()) 
        {
            CourseRoleMapper row = new CourseRoleMapper();
            row.setRole_id(resultSet.getString("role_id"));
            row.setUser_id(resultSet.getString("user_id"));
            row.setCourse_id(resultSet.getString("course_id"));
            
            rows.add(row);
            
        }

    return rows;

	}
}
