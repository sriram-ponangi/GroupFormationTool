package com.group14.app.utils;

import org.springframework.stereotype.Component;
import com.group14.app.services.*;
import com.group14.app.models.AppUser;
import com.group14.app.models.SQLInput;
import com.group14.app.repositories.*;

@Component
public class DependencyInjector {
	private static DependencyInjector dependencyInjector;

	private CRUDRepository<SQLInput> CRUDRepository;

	private IPasswordReposiotry IPasswordReposiotry;
	private IPasswordService IPasswordService;

	private IParseUploadedFile<AppUser> IParseUploadedFile;
	private IStudentEnrollmentRepository IStudentEnrollmentRepository;
	private IEmailSenderService IEmailSenderService;
	private IStudentEnrollmentService IStudentEnrollmentService;

	private DependencyInjector() {
		this.CRUDRepository = new MySQLDBOperations();

		this.IPasswordReposiotry = new PasswordReposiotry(this.CRUDRepository);
		this.IPasswordService = new PasswordService(this.IPasswordReposiotry);

		this.IParseUploadedFile = new UsersListCSVParserService();
		this.IStudentEnrollmentRepository = new StudentEnrollmentRepository(this.CRUDRepository);
		this.IEmailSenderService = new EmailSenderService();
		this.IStudentEnrollmentService = new StudentEnrollmentService(this.IStudentEnrollmentRepository,
				this.IParseUploadedFile, this.IEmailSenderService);

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

	public IStudentEnrollmentService getIStudentEnrollmentService() {
		return IStudentEnrollmentService;
	}

	public void setIStudentEnrollmentService(IStudentEnrollmentService iStudentEnrollmentService) {
		IStudentEnrollmentService = iStudentEnrollmentService;
	}

	public IStudentEnrollmentRepository getIStudentEnrollmentRepository() {
		return IStudentEnrollmentRepository;
	}

	public void setIStudentEnrollmentRepository(IStudentEnrollmentRepository iStudentEnrollmentRepository) {
		IStudentEnrollmentRepository = iStudentEnrollmentRepository;
	}

	public IParseUploadedFile<AppUser> getIParseUploadedFile() {
		return IParseUploadedFile;
	}

	public void setIParseUploadedFile(IParseUploadedFile<AppUser> iParseUploadedFile) {
		IParseUploadedFile = iParseUploadedFile;
	}

}
