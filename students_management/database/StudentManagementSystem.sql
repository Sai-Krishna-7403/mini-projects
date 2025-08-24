-- create the database
create database studentdb;

-- use database
use studentdb;

-- create the table and insert the columns
create table students(
id int primary key not null,
name varchar(50) not null,
age int not null, 
course varchar(50) not null
);

-- show the tables present inside the database
show tables;

-- describe your table
desc students;

-- select the student table
select * from students;