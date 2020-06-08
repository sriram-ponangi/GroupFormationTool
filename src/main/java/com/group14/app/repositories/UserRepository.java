package com.group14.app.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;
import com.group14.app.utils.MySQLDBOperations;

@Repository
public class UserRepository implements IUserRepository {

	private CRUDRepository<SQLInput> db = new MySQLDBOperations();

	@Override
	public boolean addUser(String bno, String fname, String lname, String email, String pass) {
		String SQL_INSERT_USER = "insert into Users ( user_id, password, first_name, last_name, email) values (?,?,?,?,?)";
		List<String> params1 = new ArrayList<>();
		params1.add(bno);
		params1.add(pass);
		params1.add(fname);
		params1.add(lname);
		params1.add(email);

		SQLInput sql1 = new SQLInput(SQL_INSERT_USER, params1);

		String SQL_INSERT_ROLE = "insert into SystemRoleMapper ( role_id, user_id) values ('GUEST',?)";
		List<String> params2 = new ArrayList<>();
		params2.add(bno);

		SQLInput sql2 = new SQLInput(SQL_INSERT_ROLE, params2);

		List<SQLInput> sqlList = new ArrayList<>();
		sqlList.add(sql1);
		sqlList.add(sql2);

		db.saveTransaction(sqlList);

		return true;
	}

}
