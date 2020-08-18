CREATE TABLE k_university(
	id NUMBER PRIMARY KEY,
	name VARCHAR(150) NOT NULL UNIQUE,
	establishment_date DATE
);

CREATE SEQUENCE K_UNIVERSITY_SEQ
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 20;

CREATE OR REPLACE TRIGGER k_university_trigger
  BEFORE INSERT ON k_university
  FOR EACH ROW
BEGIN
  SELECT K_UNIVERSITY_SEQ.nextval
  INTO :new.id
  FROM dual;
END;

 -----------------------------------------------------------------------------------------------------
 
 CREATE TABLE k_college(
	id NUMBER PRIMARY KEY,
	university_id NUMBER,
	name VARCHAR(150) NOT NULL UNIQUE,
	establishment_date DATE,
	FOREIGN KEY (university_id) REFERENCES k_university(id)
);

CREATE SEQUENCE k_college_seq
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 20;

CREATE OR REPLACE TRIGGER k_college_trigger
	BEFORE INSERT ON k_college
	FOR EACH ROW
BEGIN
	SELECT k_college_seq.nextval
	INTO : new.id
	FROM DUAL;
END;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE k_department(
  id INT PRIMARY KEY,
  college_id NUMBER NOT NULL,
  name VARCHAR(100) NOT NULL UNIQUE,
  FOREIGN KEY (college_id) REFERENCES k_college(id)
);

CREATE sequence k_department_seq
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 20;

CREATE OR REPLACE TRIGGER k_department_trigger
	BEFORE INSERT ON k_department
	FOR EACH ROW
BEGIN
	SELECT k_department_seq.nextval
	INTO : new.id
	FROM DUAL;
END;

----------------------------------------------------------------------------------------------

CREATE TABLE k_student (
id NUMBER PRIMARY KEY,
department_id NUMBER NOT NULL,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100),
mobile_no NUMBER(10) UNIQUE,
FOREIGN KEY (department_id) REFERENCES k_department(id) 
);

CREATE sequence k_student_seq
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 20;

CREATE OR REPLACE TRIGGER k_student_trigger
	BEFORE INSERT ON k_student
	FOR EACH ROW
BEGIN
	SELECT k_student_seq.nextval
	INTO : new.id
	FROM DUAL;
END;

--------------------------------------------------------------------------------------
CREATE TABLE k_employee (
id INT PRIMARY KEY,
department_id NUMBER,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100),
mobile_no INT UNIQUE,
salary NUMBER,
dob DATE,
FOREIGN KEY (department_id) REFERENCES k_department(id) 
);

CREATE sequence k_employee_seq
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 20;

CREATE OR REPLACE TRIGGER k_employee_trigger
	BEFORE INSERT ON k_employee
	FOR EACH ROW
BEGIN
	SELECT k_employee_seq.nextval
	INTO : new.id
	FROM DUAL;
END;