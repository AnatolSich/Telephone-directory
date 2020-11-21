DROP TABLE IF EXISTS phone_numbers;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS phone_companies;

DROP SEQUENCE IF EXISTS seq_books;
DROP SEQUENCE IF EXISTS seq_students;

CREATE SEQUENCE seq_students START 1000;
CREATE SEQUENCE seq_books START 5000;

CREATE TABLE students (
  id INT PRIMARY KEY DEFAULT nextval('seq_students'),
  name VARCHAR(50) NOT NULL
);

CREATE TABLE books(
  id INT PRIMARY KEY  DEFAULT nextval('seq_books'),
  title VARCHAR(50) NOT NULL ,
  studentId INT NOT NULL ,
  FOREIGN KEY (studentId) REFERENCES students (id) ON DELETE CASCADE 
);

