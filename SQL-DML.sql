--
INSERT INTO SystemRoles
  ( role, description )
  VALUES
  ('GUEST', 'User can only view the available courses'),
  ('ADMIN', 'User can perform all the admininstrative tasks');
    
SELECT * FROM SystemRoles;
--
INSERT INTO CourseRoles
  ( role, description )
  VALUES
  ('STUDENT', 'User enrolled to a course as Student'),
  ('TA', 'User enrolled to a course as Teaching Assistant'),
  ('INSTRUCTOR', 'User enrolled to a course as instructor');
  
SELECT * FROM CourseRoles;
--
INSERT INTO Courses
  ( course_id, name, year, term, description )
VALUES
  ('CSCI1001', 'Course One', '2020','Summer','Some information on Course One'),
  ('CSCI1002', 'Course Two', '2020','Fall','Some information on Course Two');
  
SELECT * FROM Courses;
--
INSERT INTO Users
  ( user_id, password, first_name, last_name, email )
VALUES
  ('B00100001', 'password', 'User','One','sriram.ponangi@dal.ca'),
  ('B00100002', 'password', 'User','Two','sd240036@dal.ca'),
  ('B00100003', 'password', 'User','Three','vg653913@dal.ca'),
  ('B00100004', 'password', 'User','Four','hr822264@dal.ca'),
  ('B00100005', 'password', 'User','Five','group14.guest.test@dal.ca'),
  ('B00100006', 'password', 'User','Six','group14.admin.test@dal.ca'),
  ('B00100007', 'password', 'User','Seven','group14.instructor.1.test@dal.ca'),
  ('B00100008', 'password', 'User','Eight','group14.instructor.2.test@dal.ca'),
  ('B00100009', 'password', 'User','Nine','group14.9.test@dal.ca'),
  ('B00100010', 'password', 'User','Ten','group14.10.test@dal.ca'),
  ('B00100011', 'password', 'User','Eleven','group14.11.test@dal.ca'),
  ('B00100012', 'password', 'User','Twelve','group14.12.test@dal.ca'),
  ('B00100013', 'password', 'User','Thirteen','group14.13.test@dal.ca'),
  ('B00100014', 'password', 'User','Fourteen','group14.14.test@dal.ca'),
  ('B00100015', 'password', 'User','Fifteen','group14.15.test@dal.ca'),
  ('B00100016', 'password', 'User','Sixteen','group14.16.test@dal.ca'),
  ('B00100017', 'password', 'User','Seventeen','group14.17.test@dal.ca'),
  ('B00100018', 'password', 'User','Eighteen','group14.18.test@dal.ca'),
  ('B00100019', 'password', 'User','Nineteen','group14.19.test@dal.ca'),
  ('B00100020', 'password', 'User','Twenty','group14.20.test@dal.ca');

SELECT * FROM Users;
--
INSERT INTO SystemRoleMapper
  ( role_id, user_id)
  VALUES
  ('GUEST','B00100001'),
  ('GUEST','B00100002'),
  ('GUEST','B00100003'),
  ('GUEST','B00100004'),
  ('GUEST','B00100005'),
  ('ADMIN','B00100006'),
  ('GUEST','B00100007'),
  ('GUEST','B00100008'),
  ('GUEST','B00100009'),
  ('GUEST','B00100010'),
  ('GUEST','B00100011'),
  ('GUEST','B00100012'),
  ('GUEST','B00100013'),
  ('GUEST','B00100014'),
  ('GUEST','B00100015'),
  ('GUEST','B00100016'),
  ('GUEST','B00100017'),
  ('GUEST','B00100018'),
  ('GUEST','B00100019'),
  ('GUEST','B00100020');
  
Select * from SystemRoleMapper;
--
INSERT INTO CourseRoleMapper 
( course_id, user_id, role_id)
VALUES
('CSCI1001' , 'B00100001', 'STUDENT'),
('CSCI1002' , 'B00100001', 'STUDENT'),

('CSCI1001' , 'B00100002', 'STUDENT'),
('CSCI1002' , 'B00100002', 'TA'),

('CSCI1001' , 'B00100003', 'TA'),
('CSCI1002' , 'B00100003', 'STUDENT'),

('CSCI1001' , 'B00100004', 'TA'),
('CSCI1002' , 'B00100004', 'TA'),

('CSCI1001' , 'B00100007', 'INSTRUCTOR'),

('CSCI1002' , 'B00100008', 'INSTRUCTOR'),

('CSCI1001' , 'B00100009', 'STUDENT'),
('CSCI1001' , 'B00100010', 'STUDENT'),
('CSCI1001' , 'B00100011', 'STUDENT'),
('CSCI1001' , 'B00100012', 'STUDENT'),
('CSCI1001' , 'B00100013', 'STUDENT'),
('CSCI1001' , 'B00100014', 'STUDENT'),
('CSCI1001' , 'B00100015', 'STUDENT'),
('CSCI1001' , 'B00100016', 'STUDENT'),
('CSCI1001' , 'B00100017', 'STUDENT'),
('CSCI1001' , 'B00100018', 'STUDENT'),
('CSCI1001' , 'B00100019', 'STUDENT'),
('CSCI1001' , 'B00100020', 'STUDENT');


  
Select * from CourseRoleMapper;
--

INSERT INTO PasswordRules
  ( rule_id, regular_expression, min_match_count, max_match_count, description)
  VALUES
  ('PASSWORD_HISTORY', '', 3, -1, "The password cannot be same as any of your last 3 passwords"),
  ('TotalLength', '\\S', 6, 20, "Length of the password should be a minimum of 6 and a maximum of 20"),
  ('UpperCaseCharactersLength', '[A-Z]', 1, -1, "No. of Upper Case characters in the password should be a minimum of 1"),
  ('LowerCaseCharactersLength', '[a-z]', 1, -1, "No. of Lower Case characters in the password should be a minimum of 1"),
  ('SpecialCharactersLength', '[^A-Za-z0-9]', 1, -1, "No. of Special characters in the password should be a minimum of 1");

-- UPDATE PasswordRules SET min_match_count = 6 WHERE rule_id = 'TotalLength';
-- UPDATE PasswordRules SET enabled = 1 WHERE rule_id = 'SpecialCharactersLength';
 SELECT * FROM PasswordRules WHERE enabled = 1;
 
INSERT INTO PasswordHistory ( user_id, password) VALUES ('B00100001', 'Test@111');
INSERT INTO PasswordHistory ( user_id, password) VALUES   ('B00100001', 'Test@222');
INSERT INTO PasswordHistory ( user_id, password) VALUES  ('B00100001', 'Test@333');
INSERT INTO PasswordHistory ( user_id, password) VALUES   ('B00100001', 'Test@444');

SELECT * FROM PasswordHistory;





INSERT INTO QuestionTypes ( type, description) VALUES
 ('MCQS', 'Multiple chocie questions with single answer'),
 ('MCQM', 'Multiple chocie questions with multiple answers'),
 ('NUMERIC', 'Questions with numerical answers'),
 ('TEXT', 'Questions with text answers');
 
SELECT * FROM  QuestionTypes;
 

INSERT INTO AllQuestions (instructor_id, title, text, type) VALUES
('B00100007', 'QUESTION-1', 'Some detailed explanation of question 1', 'MCQS'),
('B00100007', 'QUESTION-2', 'Some detailed explanation of question 2', 'MCQS'),
('B00100007', 'QUESTION-3', 'Some detailed explanation of question 3', 'MCQS'),

('B00100007', 'QUESTION-4', 'Some detailed explanation of question 4', 'MCQM'),
('B00100007', 'QUESTION-5', 'Some detailed explanation of question 5', 'MCQM'),
('B00100007', 'QUESTION-6', 'Some detailed explanation of question 6', 'MCQM'),

('B00100007', 'QUESTION-7', 'Some detailed explanation of question 7', 'TEXT'),
('B00100007', 'QUESTION-8', 'Some detailed explanation of question 8', 'TEXT'),
('B00100007', 'QUESTION-9', 'Some detailed explanation of question 9', 'TEXT'),

('B00100007', 'QUESTION-10', 'Some detailed explanation of question 10', 'NUMERIC'),
('B00100007', 'QUESTION-11', 'Some detailed explanation of question 11', 'NUMERIC'),
('B00100007', 'QUESTION-12', 'Some detailed explanation of question 12', 'NUMERIC'),
('B00100007', 'QUESTION-13', 'Some detailed explanation of question 13', 'NUMERIC'),
('B00100007', 'QUESTION-14', 'Some detailed explanation of question 14', 'NUMERIC'),
('B00100007', 'QUESTION-15', 'Some detailed explanation of question 15', 'NUMERIC'),
('B00100007', 'QUESTION-16', 'Some detailed explanation of question 16', 'NUMERIC');

SELECT * FROM  AllQuestions;

-- DELETE FROM MultipleChoiceQuestions WHERE stored_as > 0 and question_id > 0;

INSERT INTO  MultipleChoiceQuestions (question_id, stored_as, display_text) VALUES
(1, 1, 'QUESTION-1 OPTION-1'),(1, 2, 'QUESTION-1 OPTION-2'),(1, 3, 'QUESTION-1 OPTION-3'),

(2, 1, 'QUESTION-2 OPTION-1'),(2, 2, 'QUESTION-2 OPTION-2'),(2, 3, 'QUESTION-2 OPTION-3'),(2, 4, 'QUESTION-2 OPTION-4'),

(3, 1, 'QUESTION-3 OPTION-1'),(3, 2, 'QUESTION-3 OPTION-2'),(3, 3, 'QUESTION-3 OPTION-3'),(3, 4, 'QUESTION-3 OPTION-4'),(3, 5, 'QUESTION-3 OPTION-5'),

(4, 1, 'QUESTION-4 OPTION-1'),(4, 2, 'QUESTION-4 OPTION-2'),(4, 3, 'QUESTION-4 OPTION-3'),

(5, 1, 'QUESTION-5 OPTION-1'),(5, 2, 'QUESTION-5 OPTION-2'),(5, 3, 'QUESTION-5 OPTION-3'),(5, 4, 'QUESTION-5 OPTION-4'),

(6, 1, 'QUESTION-6 OPTION-1'),(6, 2, 'QUESTION-6 OPTION-2'),(6, 3, 'QUESTION-6 OPTION-3'),(6, 4, 'QUESTION-6 OPTION-4'),(6, 5, 'QUESTION-6 OPTION-5')
;

SELECT * FROM  MultipleChoiceQuestions;



INSERT INTO  Surveys (course_id, group_size) VALUES
('CSCI1001', 3);

SELECT * FROM  Surveys;




INSERT INTO  GroupFormationAlgorithmRules (name, question_type, description) VALUES
('GROUP_SIMILAR', 'MCQS', 'Group students with similar responses'), 
('GROUP_DISSIMILAR', 'MCQS', 'Group students with dissimilar responses'),

('GROUP_SIMILAR', 'MCQM', 'Group students by the more responses the same'),
('GROUP_DISSIMILAR', 'MCQM', 'Group students by the less responses the same'),

('GROUP_MATCHING_TEXT', 'TEXT', 'Group students with matching text'),
('GROUP_NON_MATCHING_TEXT', 'TEXT', 'Group students with text that does not match'),

('GROUP_SIMILAR', 'NUMERIC', 'Group students with similar responses'),
('GROUP_DISSIMILAR', 'NUMERIC', 'Group students with dissimilar responses'),
('AT_LEAST_ONE_GREATER_THAN_X', 'NUMERIC', 'Group has least one student with response greater than entered value.'),
('AT_LEAST_ONE_LESS_THAN_X', 'NUMERIC', 'Group has least one student with response less than entered value.')
;

SELECT * FROM  GroupFormationAlgorithmRules;





INSERT INTO  SurveyQuestionsMapper (survey_id, question_id) VALUES
(1, 2),
(1, 3),

(1, 5),
(1, 6),

(1, 7),
(1, 8),

(1, 10),
(1, 12),
(1, 14),
(1, 16);

SELECT * FROM SurveyQuestionsMapper;


INSERT INTO  SurveyRuleMapper (response_id, rule_id, additional_info) VALUES
(1, 1, ''),
(2, 2, ''),

(3, 3, ''),
(4, 4, ''),

(5, 5, 'scrum'),
(6, 6, 'scrum'),

(7, 7, ''),
(8, 8, ''),
(9, 9, 75),
(10, 10, 75);

SELECT * FROM SurveyRuleMapper;

-- INSERT INTO NumericResponses (response_id, student_id, answer) VALUES
-- ();
-- INSERT INTO TextResponses (response_id, student_id, answer) VALUES
-- ();