package com.group14.app.utils;

import com.group14.app.services.IPasswordService;
import com.group14.app.services.PasswordService;

import org.springframework.stereotype.Component;

import com.group14.app.models.SQLInput;
import com.group14.app.repositories.IPasswordReposiotry;
import com.group14.app.repositories.PasswordReposiotry;
@Component
public class DependencyInjector {
	private static DependencyInjector dependencyInjector;
	
	private CRUDRepository<SQLInput> CRUDRepository;
	private IPasswordService IPasswordService;
	private IPasswordReposiotry IPasswordReposiotry;
	
	private DependencyInjector()
	{		
		this.CRUDRepository = new MySQLDBOperations();
		this.IPasswordReposiotry = new PasswordReposiotry(this.CRUDRepository);	
		this.IPasswordService = new PasswordService(this.IPasswordReposiotry);					
	}
	
	public static DependencyInjector getInstance() {
		if (dependencyInjector == null) {
			dependencyInjector = new DependencyInjector();
		}
		return dependencyInjector;
	}

	public CRUDRepository<SQLInput> getCRUDRepository() {
		return CRUDRepository;
	}

	public void setCRUDRepository(CRUDRepository<SQLInput> cRUDRepository) {
		CRUDRepository = cRUDRepository;
	}

	public IPasswordService getIPasswordService() {
		return IPasswordService;
	}

	public void setIPasswordService(IPasswordService iPasswordService) {
		IPasswordService = iPasswordService;
	}

	public IPasswordReposiotry getIPasswordReposiotry() {
		return IPasswordReposiotry;
	}

	public void setIPasswordReposiotry(IPasswordReposiotry iPasswordReposiotry) {
		IPasswordReposiotry = iPasswordReposiotry;
	}	
}
