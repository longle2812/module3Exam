create database module3Exam;
use module3Exam;

create table Category(
                         id int primary key,
                         name varchar(200)
);

create table Products(
                         id int primary key ,
                         name varchar(200),
                         price double,
                         amount int,
                         color varchar(50),
                         description varchar(200),
                         category int,
                         foreign key (category) references Category(id)
);
