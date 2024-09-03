select * from klr.Userlist
create table klr.Userlist(
    id int identity(1001,1) not null,
    userName varchar(30) not null,
    userEmail varchar(50) not null,
    userPhoneNo varchar(10) not null,
    userPassword varchar(40) not null,
    SecertKey varchar(20),
    Requested int DEFAULT 0,
    apporvalmsg varchar(100),
    TypesMembership varchar(10) DEFAULT 'customer',
    isDelete int DEFAULT 0,
    Lastmodified date,
    LastmodifiedBy Varchar(30)
); 
select COUNT(*) FROM klr.Hotels where  id=1006
select COUNT(*) FROM klr.BookingLists
select COUNT(*) FROM klr.Roomlists
select COUNT(*) FROM klr.userDeatails
select COUNT(*) FROM klr.ApprovalRequests

select * FROM klr.Hotels where id=1006
select * from klr.Hotels WHERE Htisdeleted=0 and HTstatus=1 and id=1006

select * FROM klr.Hotels where Hotelid in (4,5,1)

select * from klr.Userlist where isDelete=0

insert into klr.Userlist(userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values('rupesh0','rup0@datazoic.com','7075608979','KLR@pesh','appleIShealty','owner')

insert into klr.Userlist(userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values('rupesh1','rup1@datazoic.com','7075608979','KLR@pesh','appleIShealty','owner')

insert into klr.Userlist(userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values('rupeshco','rupco@datazoic.com','7075608979','KLR@pesh','appleIShealty','owner')


insert into klr.Userlist(userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values('rupesh0','rup0@datazoic.com','7075608979','KLR@pesh','appleIShealty','customer')

insert into klr.Userlist(userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values('rupesh1','rup1@datazoic.com','7075608979','KLR@pesh','appleIShealty','customer')

insert into klr.Userlist(userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values('rupesh2','rup2@datazoic.com','7075608979','KLR@pesh','appleIShealty','customer')

insert into klr.Userlist(userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values('rupesha','rupa@datazoic.com','7075608979','KLR@pesh','appleIShealty','admin')


select id,userEmail,userPassword from klr.Userlist where isDelete=0	and  TypesMembership='admin'