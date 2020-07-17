SELECT * FROM PasswordHistory WHERE user_id = 'B00100001' ORDER BY created_date DESC LIMIT 3;



SELECT * FROM Users;
Select * from SystemRoleMapper;
Select count(*) from CourseRoleMapper where course_id='CSCI1001' and role_id='STUDENT';
-- UPDATE SystemRoleMapper SET role_id = 'B00110001' WHERE user_id = 'GUEST';-- 
-- INSERT INTO CourseRoleMapper ( role_id, course_id, user_id ) VALUES ('B00100001', ?, ?);
-- UPDATE CourseRoleMapper SET role_id = 'STUDENT' WHERE course_id = 'CDCI1001' and user_id = 'B00110001';
SELECT * FROM Users WHERE USER_ID='B00100001';
SELECT user_id, course_id FROM Users WHERE user_id= 'B00110001' AND course_id= 'CDCI1001';

-- DELETE FROM table_name WHERE USER_ID='B00100001';

show variables like "max_connections";
show processlist;




select * from Courses where course_id= 'CSCI1001';







SELECT * FROM Surveys WHERE course_id = 'CSCI1001';
SELECT * FROM SurveyQuestionsMapper WHERE survey_id = '1';

select * FROM SurveyRuleMapper;
SELECT * FROM SurveyRuleMapper WHERE response_id = 1; 