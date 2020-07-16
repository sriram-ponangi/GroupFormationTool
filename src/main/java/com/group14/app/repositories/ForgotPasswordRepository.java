package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.group14.app.models.AppUser;

import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

@Repository
public class ForgotPasswordRepository {

	private CRUDRepository<SQLInput> db;

	public ForgotPasswordRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	public String readPass(String banner) throws SQLException {
		String SQL_GET_USER = "SELECT password FROM Users WHERE USER_ID = ?";
		List<Object> params = new ArrayList<>();
		params.add(banner);
		List<HashMap<String, Object>> usersData = db.readData(new SQLInput(SQL_GET_USER, params));
		final AppUser finalPass = new AppUser();
		if (usersData != null)
			usersData.stream().forEach(row -> {
				finalPass.setPassword((String) row.get("password"));

			});
		else {
			return null;
		}

		return finalPass.getPassword();
	}

	public String readEmail(String banner) throws SQLException {

		String SQL_GET_USER = "SELECT email FROM Users WHERE USER_ID = ?";
		List<Object> params = new ArrayList<>();
		params.add(banner);
		List<HashMap<String, Object>> usersData = db.readData(new SQLInput(SQL_GET_USER, params));
		final AppUser finalPass = new AppUser();
		if (usersData != null)
			usersData.stream().forEach(row -> {
				finalPass.setEmail((String) row.get("email"));

			});
		else {
			return null;
		}

		return finalPass.getEmail();
	}

}
