package com.group14.app.repositories;

import java.sql.SQLException;

public interface IUserRepository {
	public boolean addUser(String bno, String fname, String lname, String email, String pass) throws SQLException;
}
