package com.group14.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import java.sql.*;  
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.group14.app.models.AppUser;
import com.group14.app.models.Forgotpassword;


@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	String pass;
	
	
	public EmailService(JavaMailSender javaMailSender)
	{
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMail(Forgotpassword forgotpassword) throws MailException
	{
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(forgotpassword.getEmail());
		mail.setFrom("group14sdc@gmail.com");
		mail.setSubject("Forgot Password Link");
		
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_14_DEVINT","CSCI5308_14_DEVINT_USER",
			"CSCI5308_14_DEVINT_14103");  
			
			PreparedStatement stmt=con.prepareStatement("SELECT password FROM Users WHERE EMAIL = ?"); 
			stmt.setString(1, forgotpassword.getEmail().toString());
			ResultSet rs=stmt.executeQuery();  
			
			while(rs.next())  
			{
				pass = rs.getString(1);
			}
			
			con.close();  
			}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  
		
		mail.setText("Your password is "+pass);
		javaMailSender.send(mail);
	}
}
