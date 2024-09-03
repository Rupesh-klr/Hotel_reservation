create table klr.BookingLists(
    BookingId int identity(1,1) not null,
    id int not null,
    Hostelid int not null,
    Roomid int not null,
    NoOfGuests int not null,
    BookedDate date,
    BookingFrom date,
    BookingTo date,
    TotalStay int,
    StayDetails int default 0,
    BookingisClosed int default 0,
    Colsedmsg varchar(100),
    Lastmodified date,
    LastmodifiedBy Varchar(30)
);
-- (?,?,?  ,?,  ?,?,?,?)
insert into klr.BookingLists (id,Hostelid,Roomid,NoOfGuests,BookedDate,BookingFrom,BookingTo,TotalStay) VALUES
(1001,2,8,3,getdate(),getdate(),convert(date, DATEADD(day,3,getdate()), 105),3)

update klr.BookingLists set Lastmodified=GETDATE() where BookingId=1

SET DATEFORMAT dmy;
go
select * from klr.BookingLists where BookingisClosed=0

select id,Hostelid,Roomid,NoOfGuests,CONVERT(varchar(100),BookedDate,105)as bookedon,CONVERT(varchar(100),BookingFrom,105) as BookingFrom,CONVERT(varchar(100),BookingTo,105) as BookingTo,TotalStay from klr.BookingLists where BookingisClosed=0


select id,Hostelid,Roomid,NoOfGuests,CONVERT(varchar(100),BookedDate,105)as bookedon,CONVERT(varchar(100),BookingFrom,105) as BookingFrom,
CONVERT(varchar(100),BookingTo,105) as BookingTo,TotalStay from klr.BookingLists as bl inner JOIN 


select id,Hostelid,Roomid,NoOfGuests, DATE_FORMAT(BookedDate,'%d %M %Y'),BookingFrom,BookingTo,TotalStay
from klr.BookingLists where BookingisClosed=0

select  DATE_FORMAT(getdate(),'%d %M  %Y') 
select convert(varchar, getdate(), 105)

select getdate()
select DATEADD(day,3,getdate())

select *from klr.jdbcData1 ORDER by id desc


insert into klr.jdbcData1(name,[message])values(convert(varchar, getdate(), 105),'ddd')