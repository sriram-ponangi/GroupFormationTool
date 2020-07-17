package com.group14.app.repositories;

import java.sql.SQLException;

import com.group14.app.models.AppUser;

public interface IAppUserRepository {
	public AppUser findByUserName(String id) throws SQLException;
}
