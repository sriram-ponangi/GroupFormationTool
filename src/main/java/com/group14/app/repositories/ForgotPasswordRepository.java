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

import com.group14.app.models.Forgotpassword;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.MySQLDBOperations;

@Repository
public class ForgotPasswordRepository {
	static Connection con = null;
	@Autowired
	private MySQLDBOperations db;
	
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
	
	public String readPass(String banner)
	{	
		String SQL_GET_USER = "SELECT password FROM Users WHERE USER_ID = ?";
		List<String> params = new ArrayList<>();
		params.add(banner);
		List<HashMap<String,Object>> usersData = db.readData(new SQLInput( SQL_GET_USER, params));
		final Forgotpassword finalPass = new Forgotpassword();
		if(usersData!=null)
			usersData.stream().forEach(row -> {
				finalPass.setPassword((String) row.get("password"));
				
			});
		else {
//			LOG.error("Could not Execute: "+ SQL_GET_USER);
			return null;		
		}
//		try{  
//			
//			PreparedStatement stmt=con.prepareStatement("SELECT password FROM Users WHERE USER_ID = ?"); 
//			stmt.setString(1, pass.toString());
//			ResultSet rs=stmt.executeQuery();  
//			
//			while(rs.next())  
//			{
//				pass = rs.getString(1);
//			}
//			
//			}
//		catch(Exception e)
//		{ 
//			System.out.println(e);
//		}
		
		
		return finalPass.getPassword();  
	}
	
	public String readEmail(String banner)
	{

		String SQL_GET_USER = "SELECT email FROM Users WHERE USER_ID = ?";
		List<String> params = new ArrayList<>();
		params.add(banner);
		List<HashMap<String,Object>> usersData = db.readData(new SQLInput( SQL_GET_USER, params));
		final Forgotpassword finalPass = new Forgotpassword();
		if(usersData!=null)
			usersData.stream().forEach(row -> {
				finalPass.setEmail((String) row.get("email"));
				
			});
		else {
//			LOG.error("Could not Execute: "+ SQL_GET_USER);
			return null;		
		}
		
//		try{  
//			PreparedStatement stmt=con.prepareStatement("SELECT email FROM Users WHERE USER_ID = ?"); 
//			stmt.setString(1, banner.toString());
//			ResultSet rs=stmt.executeQuery();  
//			
//			while(rs.next())  
//			{
//				banner = rs.getString(1);
//			}
//			
//			}
//		catch(Exception e)
//		{ 
//			System.out.println(e);
//		}
//		
		
		return finalPass.getEmail();  
	}
	
	
	

}
