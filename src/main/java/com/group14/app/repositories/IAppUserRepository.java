package com.group14.app.repositories;

import com.group14.app.models.AppUser;

public interface IAppUserRepository {
	public AppUser findByUserName(String id);
}
