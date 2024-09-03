create table emp_rupesh1(   
  empno    number(4,0),   
  ename    varchar2(10),   
  location    varchar2(30) default,  
  job      varchar2(9),   
  mgr      number(4,0),  
  hiredate date,   
  sal      number(7,2),   
  comm     number(7,2),
  deptno   number(2,0),  
  constraint pk_emp primary key (empno),   
  constraint fk_deptno foreign key (deptno) references dept_rupesh (deptno)  
);
 
insert into emp_rupesh   
values(   
7839, 'KING', 'PRESIDENT', null, to_date('17-11-1981','dd-mm-yyyy'), 5000, null, 10);
 
insert into emp_rupesh  
values( 7698, 'BLAKE', 'MANAGER', 7839, to_date('1-5-1981','dd-mm-yyyy'), 2850, null, 30);
 
insert into emp_rupesh  

values( 7782, 'CLARK', 'MANAGER', 7839, to_date('9-6-1981','dd-mm-yyyy'), 2450, null, 10);
 
insert into emp_rupesh  

values(7566, 'JONES', 'MANAGER', 7839, to_date('2-4-1981','dd-mm-yyyy'), 2975, null, 20);
 
insert into emp_rupesh  

values( 7788, 'SCOTT', 'ANALYST', 7566, to_date('13-JUL-87','dd-mm-rr') - 85, 3000, null, 20);
 
insert into emp_rupesh  

values(7902, 'FORD', 'ANALYST', 7566, to_date('3-12-1981','dd-mm-yyyy'),  3000, null, 20);
 
insert into emp_rupesh  

values(7369, 'SMITH', 'CLERK', 7902, to_date('17-12-1980','dd-mm-yyyy'),  800, null, 20);
 
insert into emp_rupesh  

values(7499, 'ALLEN', 'SALESMAN', 7698, to_date('20-2-1981','dd-mm-yyyy'), 1600, 300, 30);
 
insert into emp_rupesh  

values(7521, 'WARD', 'SALESMAN', 7698, to_date('22-2-1981','dd-mm-yyyy'), 1250, 500, 30);
 
insert into emp_rupesh  
values(7654, 'MARTIN', 'SALESMAN', 7698, to_date('28-9-1981','dd-mm-yyyy'), 1250, 1400, 30);
 
insert into emp_rupesh  

values(7844, 'TURNER', 'SALESMAN', 7698, to_date('8-9-1981','dd-mm-yyyy'), 1500, 0, 30);
 
insert into emp_rupesh  

values(7876, 'ADAMS', 'CLERK', 7788, to_date('13-JUL-87', 'dd-mm-rr') - 51, 1100, null, 20);
 
insert into emp_rupesh  

values(7900, 'JAMES', 'CLERK', 7698, to_date('3-12-1981','dd-mm-yyyy'), 950, null, 30);
 
insert into emp_rupesh  

values(7934, 'MILLER', 'CLERK', 7782, to_date('23-1-1982','dd-mm-yyyy'), 1300, null, 10);
 
  create table emp_rupesh_1(   
  empno    number(4,0),   
  ename    varchar2(10),   
  location    varchar2(30) default 'banglore',  
  job      varchar2(9),   
  mgr      number(4,0),  
  hiredate date,   
  sal      number(7,2),   
  comm     number(7,2),
  deptno   number(2,0),  
  constraint pk_emp1 primary key (empno),   
  constraint fk_deptno1 foreign key (deptno) references dept_rupesh (deptno)  
);



insert into emp_rupesh_1   ( empno   ,    ename     ,  job   ,   mgr ,     hiredate  ,  sal ,      comm  ,   deptno  )
values(   7830, 'KING', 'PRESIDENT', null, to_date('17-11-1981','dd-mm-yyyy'), 5000, null, 10);


create table emp_rupesh_2(   
  empno    number(4,0),   
  ename    varchar2(10),   
  location    varchar2(30) check (location<>'banglore'),  
  job      varchar2(9),   
  mgr      number(4,0),  
  hiredate date,   
  sal      number(7,2),   
  comm     number(7,2),
  deptno   number(2,0),  
  constraint pk_emp2 primary key (empno),   
  constraint fk_deptno2 foreign key (deptno) references dept_rupesh (deptno)  
);


insert into emp_rupesh_2   ( empno,ename,location,  job   ,mgr ,hiredate  ,sal ,comm  ,deptno  )
values(   7830,'banglore', 'KING', 'PRESIDENT', null, to_date('17-11-1981','dd-mm-yyyy'), 5000, null, 10);

Error report -
ORA-02290: check constraint (SYSTEM.SYS_C007536) violated

DESC TABLE name 

create table emp_rupesh_3(   
  empno    number(4,0),   
  ename    varchar2(10) unique,  
  job      varchar2(9),   
  age       number(2,0) check(age<=60) not null,
  mgr      number(4,0) not null,  
  hiredate date    ,   
  sal      number(7,2) not null,   
  comm     number(7,2),
  deptno   number(2,0) not null,  
  constraint pk_emp_3 primary key (empno),   
  constraint fk_deptno_3 foreign key (deptno) references dept_rupesh (deptno)  
);


-- insert into emp_rupesh_1   ( empno   ,    ename     ,  job   ,   mgr ,     hiredate  ,  sal ,      comm  ,   deptno  )
-- values 




select * from emp_rupesh where ename = 'KING'


select * from emp_rupesh where comm is not null

insert into emp_rupesh  
values(7902, 'FORD', 'ANALYST', 7566, to_date('3-12-1981','dd-mm-yyyy'),  3000, null, 20);

select * from emp_rupesh where Mgr= (select empno from emp_rupesh where ename = 'KING' ) and  comm is not null

select EMPNO ,	ENAME,	JOB,	MGR,	HIREDATE,	SAL*12 as MONTHLY_SALARy,	COMM,	DEPTNO from emp_rupesh 
where sal*12 >30000;

select EMPNO as value,	ENAME,	JOB,	MGR,	HIREDATE,	SAL,	COMM,	DEPTNO from emp_rupesh where sal >6000



select * from dept_rupesh
select deptno from dept_rupesh where dname = 'ACCOUNTING';



select  EMPNO ,	ENAME,	JOB,	MGR,	HIREDATE,	SAL*12 as YEARLY_SALARy,	COMM,	DEPTNO from emp_rupesh 
where deptno = (select deptno from dept_rupesh where dname = 'ACCOUNTING') order by deptno,sal desc

select * from emp_rupesh order by deptno asc ,sal desc

select * from dept_rupesh

select * from emp_rupesh

select E.ename
,D.dname,D.loc
,E.sal
from emp_rupesh  E left join dept_rupesh  D on (E.DEPTNO = D.DEPTNO )
order by E.DEPTNO

 

select * from emp_rupesh where deptno is  null

select * from emp_rupesh where deptno not in (select deptno from dept_rupesh)



select * from emp_rupesh where ename like 'A%'


--#FORD1
select * from emp_rupesh where ename like '%_FORD%' ESCAPE '\'


select * from emp_rupesh where ename like '%_%' ESCAPE '\'


insert into emp_rupesh  
values(732, '#FORD1', 'ANALYST', 7566, to_date('3-12-1981','dd-mm-yyyy'),  3000, null, 20);



select * from emp_rupesh where job = 'MANAGER' or job is not null



select * from emp_rupesh where 1=1
and ( job = 'MANAGER' and ename like 'B%' )
or comm is not null

select * from emp_rupesh where not  ename like 'S%'

select * from emp_rupesh where   job <>'PRESIDENT'



select * from emp_rupesh where 
sal between 2500 and 3500
and deptno in (10,20)
--sal >2500 and sal<3500
