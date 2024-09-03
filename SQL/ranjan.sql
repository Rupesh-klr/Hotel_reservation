use world;


drop table role_ids;
drop table employee_details;
drop table tasks;
drop table task_assignment;

truncate table role_ids;
truncate table employee_details;
truncate table tasks;
truncate table task_assignment;


create table role_ids(
role_id int auto_increment primary key,
role_name varchar(20) not null,
branch varchar(30) not null
);

create table employee_details(
emp_id int auto_increment primary key,
emp_name varchar(100) NOT NULL default '',
emp_email varchar(100) default '',
phone bigint default 0,
gender ENUM('Male', 'Female') NOT NULL,
role_id int ,
foreign key (role_id) REFERENCES role_ids(role_id)
);

drop table tasks;
create table tasks(
task_id int auto_increment primary key,
topic_title varchar(100) not null,
description varchar(300)  default '',
comment_note varchar(300) default '',
create_date datetime DEFAULT CURRENT_TIMESTAMP,
due_time datetime  DEFAULT CURRENT_TIMESTAMP
);

create table task_assignment(
assignment_id	int auto_increment primary key,
task_id int not null,
assigner_id int not null,
assignee_id int not null,
status  ENUM('Assigned', 'InProgress','completed','over due') default 'Assigned',
foreign key (assigner_id) REFERENCES employee_details(emp_id),
foreign key (assignee_id) REFERENCES employee_details(emp_id),
foreign key (task_id) REFERENCES tasks(task_id)
);

select * from  role_ids;
select * from  employee_details;
select * from  tasks;
select * from  task_assignment;



-- getall employee with role name and brach 
select emptb.emp_id,roletb.role_name, roletb.branch, emptb.emp_name, emptb.emp_email, emptb.phone,
 emptb.gender  from employee_details emptb join role_ids roletb on
emptb.role_id = roletb.role_id;


INSERT INTO `world`.`employee_details`
(`emp_name`,`emp_email`,`phone`,`gender`,`role_id`)
VALUES ( 'vimal','vimal1@gmail.com',9988776601,'Male',1)
,( 'suthersan','suthersan@gmail.com',9988776602,'Male',2)
,( 'ravindhar','ravindhar@gmail.com',9988776603,'Male',2)
,( 'venkatesh','venkatesh@gmail.com',9988776604,'Male',3)
,( 'sabari','sabari@gmail.com',9988776605,'Male',3)
,( 'rithisha','rithisha@gmail.com',9988776605,'Female',3) ;


INSERT INTO `world`.`role_ids`
(`role_name`,`branch`)
VALUES ( 'admin','mass_market'),('manger','mass_market'),('sde','mass_market');


INSERT INTO `world`.`tasks`
(`topic_title`,`description`,`comment_note`,`due_time`) values 
-- ('spring webservices','Important topics to know for upcoming project.','Complete this project and report me.','2024-03-14T00:00:00'),
('Core Java','Important topics to know for upcoming project.','Complete this project and report me.','2024-03-14T00:00:00'),('Oracel Sql basics topices.','Important topics to know for upcoming project.','Complete this project and report me.','2024-03-14T00:00:00') ;



INSERT INTO `world`.`task_assignment`
(`task_id`,`assigner_id`,`assignee_id`)
VALUES (1,2,4),(2,3,4)  ,(1,2,5),(2,3,5),  (2,3,6),(3,1,6);

-- get all employees indivally about how task and the given task status.
SELECT 
    emptb.emp_id,
    t.task_id,
    roletb.role_name, 
 --   roletb.branch,     emptb.emp_email,     emptb.phone,      emptb.gender,    t.create_date,     t.comment_note, 
    emptb.emp_name, 
    t.topic_title, 
    t.description,  
    t.due_time, 
    ta.status,
    CASE 
        WHEN emptb.emp_id = ta.assigner_id THEN concat( 'Given to ',(select emp_name from employee_details where emp_id= ta.assignee_id))
        WHEN emptb.emp_id = ta.assignee_id THEN concat('My assigment given by  ',(select emp_name from employee_details where emp_id= ta.assigner_id) )
        ELSE 'NTA'  
    END AS Task_details  
FROM 
    employee_details emptb   
JOIN 
    role_ids roletb ON emptb.role_id = roletb.role_id   
JOIN 
    task_assignment ta ON emptb.emp_id = ta.assigner_id OR emptb.emp_id = ta.assignee_id   
JOIN 
    tasks t ON ta.task_id = t.task_id  
GROUP BY 
    emptb.emp_id,
    t.task_id,
     ta.status,
    ta.assigner_id,
    ta.assignee_id
    order by emptb.emp_id,t.task_id
LIMIT 0, 1000;
