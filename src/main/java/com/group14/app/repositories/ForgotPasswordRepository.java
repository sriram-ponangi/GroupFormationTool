package com.group14.app.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ForgotPasswordRepository {
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
	
	public String readPass(String pass)
	{	
		try{  
			PreparedStatement stmt=con.prepareStatement("SELECT password FROM Users WHERE USER_ID = ?"); 
			stmt.setString(1, pass.toString());
			ResultSet rs=stmt.executeQuery();  
			
			while(rs.next())  
			{
				pass = rs.getString(1);
			}
			
			}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
		
		
		return pass;  
	}
	
	public String readEmail(String banner)
	{

		try{  
			PreparedStatement stmt=con.prepareStatement("SELECT email FROM Users WHERE USER_ID = ?"); 
			stmt.setString(1, banner.toString());
			ResultSet rs=stmt.executeQuery();  
			
			while(rs.next())  
			{
				banner = rs.getString(1);
			}
			
			}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
		
		
		return banner;  
	}
	
	
	

}
