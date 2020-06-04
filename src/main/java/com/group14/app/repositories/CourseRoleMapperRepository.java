package com.group14.app.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group14.app.models.CourseRoleMapper;
import com.group14.app.models.Forgotpassword;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.MySQLDBOperations;

@Repository
public class CourseRoleMapperRepository {

static Connection con = null;

@Autowired
private MySQLDBOperations db;
	
	
	public List<CourseRoleMapper> list() throws SQLException {
		
		String SQL_GET_USER = "SELECT email FROM Users WHERE USER_ID = ?";
		List<String> params = new ArrayList<>();
		params.add("INSTRUCTOR");
		final List<CourseRoleMapper> rows = new ArrayList<CourseRoleMapper>();
		
		List<HashMap<String,Object>> usersData = db.readData(new SQLInput( SQL_GET_USER, params));
		
		if(usersData!=null)
			usersData.stream().forEach(row -> {
				CourseRoleMapper cRM = new CourseRoleMapper();
				cRM.setRole_id((String) row.get("role_id"));
				cRM.setUser_id((String) row.get("user_id"));
				cRM.setCourse_id((String) row.get("course_id"));
				rows.add(cRM);
			});
		else {
//			LOG.error("Could not Execute: "+ SQL_GET_USER);
			return null;		
		}
		
		
		return rows;
		
		
//		List<CourseRoleMapper> rows = new ArrayList<CourseRoleMapper>();
//		PreparedStatement stmt=con.prepareStatement("select * from CourseRoleMapper where role_id=?"); 
//		stmt.setString(1, "INSTRUCTOR");
//		ResultSet resultSet = null;
//
//        resultSet=stmt.executeQuery();
//        
//        while (resultSet.next()) 
//        {
//            CourseRoleMapper row = new CourseRoleMapper();
//            row.setRole_id(resultSet.getString("role_id"));
//            row.setUser_id(resultSet.getString("user_id"));
//            row.setCourse_id(resultSet.getString("course_id"));
//            
//            rows.add(row);
//            
//        }

    

	}
	
	public void addCourseI(String banner, String cid) throws SQLException {
		
		String SQL_GET_USER = "insert into CourseRoleMapper(role_id,user_id,course_id) values (?,?,?)";
		List<String> params = new ArrayList<>();
		params.add("INSTRUCTOR");
		params.add(banner);
		params.add(cid);
		
		
		int usersData = db.save(new SQLInput( SQL_GET_USER, params));
		
		
		
//		PreparedStatement stmt=con.prepareStatement("insert into CourseRoleMapper(role_id,user_id,course_id)"
//				+"values (?,?,?)"); 
//				
//		stmt.setString(1,"INSTRUCTOR");
//		stmt.setString(2,banner);
//		stmt.setString(3, cid);
//		
//		stmt.execute();
	   
	}
}
