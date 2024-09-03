drop table klr.Hotels;

create table klr.Hotels(
Hotelid int identity(1,1) not null,
id int not null,
HostelName varchar(50),
HotelAddress VARCHAR(100),
HotelNumber VARCHAR(10),
HotelDesc VARCHAR(300),
Rating int ,
HotelLocation VARCHAR(50),
status int DEFAULT 0,
statusmsg VARCHAR(100),
StaringPr FLOAT,
EndPr FLOAT,
Htisdeleted int default 0,
LastmodifiedHT date ,
LastmodifiedByHT Varchar(30) 
);
-- insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation) VALUEs (?,?,?,?,?,?,?)

insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation) VALUEs
(1001,'Malika','6.no.door,pergudi,chennai(600512)','9898987654','this located in chennai',5,'chennai')

insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation) VALUEs
(1001,'kings','666.no.door,pergudi,chennai(600512)','9898987654','this located in chennai',5,'chennai')

insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation) VALUEs
(1002,'prabha grand inn','6.no.door,pergudi,chittoor(517001)','9898987654','this located in chittoor',5,'chittoor')

insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation) VALUEs
(1003,'gem minners','06\8.no.door,pergudi,tirupati(512892)','9898987654','this located in tirupati',5,'tirupati')

insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation) VALUEs
(1003,'gem beggers','99.no.door,pergudi,tirupati(512892)','9898987654','this located in tirupati',5,'tirupati')

insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation) VALUEs
(1005,'golden hotel','6.no.door,pergudi,chennai(600512)','9898987654','this located in chennai',5,'chennai')

insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation) VALUEs
(1006,'Mui000','16-67/5.no.door,pergudi,chennai(600512)','9898987654','this located in chennai',5,'chennai')

insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation) VALUEs
(1006,'goodness','17-89/3,door,pergudi,tirupati(600512)','9898987654','this located in tirupati',5,'tirupati')

insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation) VALUEs
(1006,'lovers flyer','76.no.door,pergudi,chittoor(600512)','9898987654','this located in chittoor',5,'chittoor')



select * from klr.Hotels WHERE Htisdeleted=0