package com.group14.app.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.group14.app.models.Forgotpassword;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;
import com.group14.app.utils.MySQLDBOperations;

@Repository
public class ForgotPasswordRepository {

	private CRUDRepository<SQLInput> db = new MySQLDBOperations();

	public String readPass(String banner) {
		String SQL_GET_USER = "SELECT password FROM Users WHERE USER_ID = ?";
		List<Object> params = new ArrayList<>();
		params.add(banner);
		List<HashMap<String, Object>> usersData = db.readData(new SQLInput(SQL_GET_USER, params));
		final Forgotpassword finalPass = new Forgotpassword();
		if (usersData != null)
			usersData.stream().forEach(row -> {
				finalPass.setPassword((String) row.get("password"));

			});
		else {
//			LOG.error("Could not Execute: "+ SQL_GET_USER);
			return null;
		}

		return finalPass.getPassword();
	}

	public String readEmail(String banner) {

		String SQL_GET_USER = "SELECT email FROM Users WHERE USER_ID = ?";
		List<Object> params = new ArrayList<>();
		params.add(banner);
		List<HashMap<String, Object>> usersData = db.readData(new SQLInput(SQL_GET_USER, params));
		final Forgotpassword finalPass = new Forgotpassword();
		if (usersData != null)
			usersData.stream().forEach(row -> {
				finalPass.setEmail((String) row.get("email"));

			});
		else {
//			LOG.error("Could not Execute: "+ SQL_GET_USER);
			return null;
		}

		return finalPass.getEmail();
	}

}
