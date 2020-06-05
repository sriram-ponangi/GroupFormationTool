package com.group14.app.repositories;

import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
//import java.sql.*;
//import com.mysql.cj.xdevapi.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group14.app.models.SQLInput;
import com.group14.app.utils.MySQLDBOperations;

@Repository
public class UserRepository implements IUserRepository{

	@Autowired
	MySQLDBOperations db;


	@Override
	public boolean addUser(String bno, String fname, String lname, String email, String pass) {
		String SQL_INSERT_USER = "insert into Users ( user_id, password, first_name, last_name, email) values (?,?,?,?,?)";
        List<String> params1 = new ArrayList<>();
        params1.add(bno);
        params1.add(pass);
        params1.add(fname);
        params1.add(lname);
        params1.add(email);
       
        SQLInput sql1=new SQLInput( SQL_INSERT_USER, params1);
        
    	String SQL_INSERT_ROLE = "insert into SystemRoleMapper ( role_id, user_id) values ('GUEST',?)";
        List<String> params2 = new ArrayList<>();
        params2.add(bno);

       
        SQLInput sql2=new SQLInput( SQL_INSERT_ROLE, params2);
        
        List<SQLInput> sqlList=new ArrayList<>();
        sqlList.add(sql1);
        sqlList.add(sql2);
        
        db.saveTransaction(sqlList);
        
        
//        
//        int usersData = db.save(new SQLInput( SQL_GET_USER, params));
//		try {
//			
//			PreparedStatement statement = null;
//			Connection connect=null;
//			
//			connect=DBConnection.connectDB();
//			statement = connect.prepareStatement("insert into Users values (?,?,?,?,?,1)");
//			statement.setString(1, bno);
//			statement.setString(2, pass);
//			statement.setString(3, fname);
//			statement.setString(4, lname);
//			statement.setString(5, email);
//			statement.execute();
//			
//			System.out.println("Inserted User");
//
//			
//			statement = connect.prepareStatement("insert into SystemRoleMapper values ('GUEST',?)");
//			statement.setString(1, bno);
//			statement.execute();
//		
//		System.out.println("Inserted Role");
//
//			
//
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			
//		}
		return true;
	}

}
