-- CREATE  SCHEMA `csci5308_group14` ;
USE `csci5308_group14` ; 
-- DROP schema `csci5308_group14` ;

DROP TABLE SurveyRuleMapper;
DROP TABLE TextResponses;
DROP TABLE NumericResponses;
DROP TABLE SurveyQuestionsMapper;
DROP TABLE Surveys;
DROP TABLE MultipleChoiceQuestions;
DROP TABLE AllQuestions;



DROP TABLE PasswordRules;
DROP TABLE PasswordHistory;

DROP TABLE SystemRoleMapper;
DROP TABLE CourseRoleMapper;
DROP TABLE SystemRoles;
DROP TABLE CourseRoles;
DROP TABLE Courses;
DROP TABLE Users;


DROP TABLE GroupFormationAlgorithmRules;
DROP TABLE QuestionTypes;









CREATE TABLE IF NOT EXISTS Users (
  `user_id` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50),
  `email` VARCHAR(50) NOT NULL,
  `enabled` INT NOT NULL default 1,
  PRIMARY KEY (`user_id`));


CREATE TABLE IF NOT EXISTS Courses (
  `course_id` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `year` VARCHAR(50) NOT NULL,
  `term` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
   `enabled` INT NOT NULL default 1,
   PRIMARY KEY (`course_id`));
   
CREATE TABLE IF NOT EXISTS CourseRoles (
  `role` VARCHAR(50) NOT NULL,
  `description` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`role`));

CREATE TABLE IF NOT EXISTS SystemRoles (
  `role` VARCHAR(50) NOT NULL,
  `description` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`role`));

CREATE TABLE IF NOT EXISTS CourseRoleMapper (
  `role_id` VARCHAR(50) NOT NULL,
  `user_id` VARCHAR(50) NOT NULL,
  `course_id` VARCHAR(50) NOT NULL,
  CONSTRAINT FK_Course_Role FOREIGN KEY (role_id)
    REFERENCES CourseRoles(role),
  CONSTRAINT FK_Course_user_id FOREIGN KEY (user_id)
    REFERENCES Users(user_id),
  CONSTRAINT FK_Course_id FOREIGN KEY (course_id)
    REFERENCES Courses(course_id),
  PRIMARY KEY(user_id, course_id)  
  );

CREATE TABLE IF NOT EXISTS SystemRoleMapper (
  `role_id` VARCHAR(50) NOT NULL,
  `user_id` VARCHAR(50) NOT NULL,
  CONSTRAINT FK_System_Role FOREIGN KEY (role_id)
    REFERENCES SystemRoles(role),
  CONSTRAINT FK_System_user_id FOREIGN KEY (user_id)
    REFERENCES Users(user_id),
  PRIMARY KEY(user_id)  
  );
  
  
  
 
 
 
 
CREATE TABLE IF NOT EXISTS PasswordRules (
  `rule_id`  VARCHAR(100) NOT NULL,
  `regular_expression` VARCHAR(100) NOT NULL,
  `min_match_count` int NOT NULL ,
  `max_match_count` int NOT NULL ,
  `description` VARCHAR(500) NOT NULL,
  `enabled` INT NOT NULL default 1,
  PRIMARY KEY(rule_id)   
  );
  
  CREATE TABLE IF NOT EXISTS PasswordHistory (
	`user_id` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
	`created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     CONSTRAINT FK_Password_History_user_id FOREIGN KEY (user_id)
     REFERENCES Users(user_id)
);  
  
  
  
 
 
 
CREATE TABLE IF NOT EXISTS QuestionTypes (
`type` VARCHAR(50) NOT NULL,
`description` VARCHAR(200) NOT NULL,
PRIMARY KEY(type)   
);
 
 CREATE TABLE IF NOT EXISTS AllQuestions (
	`question_id`  int NOT NULL AUTO_INCREMENT,
    `instructor_id` VARCHAR(50) NOT NULL,
    `title` VARCHAR(50) NOT NULL,
    `text` VARCHAR(500) NOT NULL,
    `type` VARCHAR(50) NOT NULL,
    `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_Instructor_id FOREIGN KEY (instructor_id)
    REFERENCES Users(user_id),
	PRIMARY KEY(question_id)   
  );  
  
  CREATE TABLE IF NOT EXISTS MultipleChoiceQuestions (
`question_id`  int NOT NULL,
`stored_as` int NOT NULL,
`display_text`  VARCHAR(150) NOT NULL,
 CONSTRAINT FK_Question_id FOREIGN KEY (question_id)
 REFERENCES AllQuestions(question_id),
 PRIMARY KEY(question_id, stored_as)   
);

CREATE TABLE IF NOT EXISTS Surveys (
`survey_id` int NOT NULL AUTO_INCREMENT,
`course_id` VARCHAR(50) NOT NULL UNIQUE,
`published` INT NOT NULL default 0,
`group_size` INT NOT NULL,
 CONSTRAINT FK_Survey_Course_id FOREIGN KEY (course_id)
 REFERENCES Courses(course_id),
 PRIMARY KEY(survey_id)   
);

CREATE TABLE IF NOT EXISTS SurveyQuestionsMapper (
`response_id` int NOT NULL AUTO_INCREMENT,
`question_id` int NOT NULL,
`survey_id` int NOT NULL,
CONSTRAINT FK_Question_id_Mapping_Survey FOREIGN KEY (question_id)
 REFERENCES AllQuestions(question_id),
 CONSTRAINT FK_Survey_id FOREIGN KEY (survey_id)
 REFERENCES Surveys(survey_id),
 PRIMARY KEY(response_id)   
);


CREATE TABLE IF NOT EXISTS NumericResponses (
`response_id` int NOT NULL ,
`student_id` VARCHAR(50) NOT NULL,
`answer` int NOT NULL,
CONSTRAINT FK_numeric_response_id FOREIGN KEY (response_id)
    REFERENCES SurveyQuestionsMapper(response_id),
CONSTRAINT FK_numeric_response_Student_id FOREIGN KEY (student_id)
    REFERENCES Users(user_id)
);
  
CREATE TABLE IF NOT EXISTS TextResponses (
`response_id` int NOT NULL ,
`student_id` VARCHAR(50) NOT NULL,
`answer` VARCHAR(500) NOT NULL,
CONSTRAINT FK_text_response_id FOREIGN KEY (response_id)
    REFERENCES SurveyQuestionsMapper(response_id),
CONSTRAINT FK_text_response_Student_id FOREIGN KEY (student_id)
    REFERENCES Users(user_id)
);  

CREATE TABLE IF NOT EXISTS GroupFormationAlgorithmRules (
`rule_id` int NOT NULL AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`question_type` VARCHAR(50) NOT NULL,
`description` VARCHAR(500) NOT NULL,
PRIMARY KEY(rule_id),
CONSTRAINT GFA_question_type FOREIGN KEY (question_type)
    REFERENCES QuestionTypes(type)
);

CREATE TABLE IF NOT EXISTS SurveyRuleMapper (
`response_id` int NOT NULL ,
`rule_id` int NOT NULL,
`additional_info` VARCHAR(100) DEFAULT NULL,
CONSTRAINT Survey_Rule_response_id FOREIGN KEY (response_id)
    REFERENCES SurveyQuestionsMapper(response_id),
CONSTRAINT Survey_Rule_rule_id FOREIGN KEY (rule_id)
    REFERENCES GroupFormationAlgorithmRules(rule_id),
PRIMARY KEY(response_id)
);