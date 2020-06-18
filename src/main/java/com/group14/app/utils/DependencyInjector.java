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

	private ICourseRepository ICourseRepository;
	private ICourseService ICourseService;
	
	private IQuestionManagerRepository IQuestionManagerRepository;
	private IQuestionManagerService IQuestionManagerService;
	
	private IAnswerManagerRepository IAnswerManagerRepository;
	private IAnswerManagerService IAnswerManagerService;
	
	private ICourseRoleMapperRepository ICourseRoleMapperRepository;
	private ICourseRoleMapperService ICourseRoleMapperService;


	private DependencyInjector() {
		this.CRUDRepository = new MySQLDBOperations();

		this.IPasswordReposiotry = new PasswordReposiotry(this.CRUDRepository);
		this.IPasswordService = new PasswordService(this.IPasswordReposiotry);

		this.IParseUploadedFile = new UsersListCSVParserService();
		this.IStudentEnrollmentRepository = new StudentEnrollmentRepository(this.CRUDRepository);
		this.IEmailSenderService = new EmailSenderService();
		this.IStudentEnrollmentService = new StudentEnrollmentService(this.IStudentEnrollmentRepository,
				this.IParseUploadedFile, this.IEmailSenderService);
		this.ICourseRepository = new CourseRepository(this.CRUDRepository);
		this.ICourseService = new CourseService(this.ICourseRepository);
		this.IQuestionManagerService = new QuestionManagerService(this.IQuestionManagerRepository);
		this.IQuestionManagerRepository = new QuestionManagerRepository(this.CRUDRepository);
		this.IAnswerManagerRepository = new AnswerManagerRepository(this.CRUDRepository);
		this.IAnswerManagerService = new AnswerManagerService(this.IAnswerManagerRepository);
		this.ICourseRoleMapperRepository = new CourseRoleMapperRepository(this.CRUDRepository);
		this.ICourseRoleMapperService = new CourseRoleMapperService(this.ICourseRoleMapperRepository);

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

	public ICourseRepository getICourseRepository() {
		return ICourseRepository;
	}

	public void setICourseRepository(ICourseRepository iCourseRepository) {
		ICourseRepository = iCourseRepository;
	}

	public ICourseService getICourseService() {
		return ICourseService;
	}

	public void setICourseService(ICourseService iCourseService) {
		ICourseService = iCourseService;
	}

	public IQuestionManagerRepository getIQuestionManagerRepository() {
		return IQuestionManagerRepository;
	}

	public void setIQuestionManagerRepository(IQuestionManagerRepository iQuestionManagerRepository) {
		IQuestionManagerRepository = iQuestionManagerRepository;
	}

	public IQuestionManagerService getIQuestionManagerService() {
		return IQuestionManagerService;
	}

	public void setIQuestionManagerService(IQuestionManagerService iQuestionManagerService) {
		IQuestionManagerService = iQuestionManagerService;
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

	public IAnswerManagerRepository getIAnswerManagerRepository() {
		return IAnswerManagerRepository;
	}

	public void setIAnswerManagerRepository(IAnswerManagerRepository iAnswerManagerRepository) {
		IAnswerManagerRepository = iAnswerManagerRepository;
	}
	
	
	public IAnswerManagerService getIAnswerManagerService() {
		return IAnswerManagerService;
	}

	public void setIAnswerManagerService(IAnswerManagerService iAnswerManagerService) {
		IAnswerManagerService = iAnswerManagerService;
	}

}
