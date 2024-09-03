----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
--appprovalRequests

--1-pan 2-adhar 3-voter 4-passport 5-driving lices


---making 1006 and 1007 as requesting to become as 
set IDENTITY_INSERT SQLTraining.klr.ApprovalRequests on

insert into klr.ApprovalRequests (ApprovalId,ProofType,Proof,AttachmentMessage,id,isClosed) VALUES 
(1,1,'PAN897867','i m real human being to live in chennai.please approve me!',1007,0),
(2,1,'PAN111111','i m real human being to live in chennai.please approve me!',1008,0)
set IDENTITY_INSERT SQLTraining.klr.ApprovalRequests off

update klr.Userlist set Requested=1 ,apporvalmsg=? where id=?
update klr.Userlist set Requested=2 ,apporvalmsg='i m real human being to live in chennai.please approve me to become as admin!'
where id=1008

select *from klr.ApprovalRequests where isClosed=0


DECLARE 
update klr.ApprovalRequests set isClosed=1,closedReplay='Thanks applying welcoming to oursite'
,closebyId=1006,closedOn=getdate() where ApprovalId=2
update klr.Userlist set TypesMembership='admin',apporvalmsg='Thanks applying welcoming to oursite' ,Lastmodified=GETDATE()
,LastmodifiedBy=(Select userName from klr.Userlist where id=1006),Requested=0 where id=(select id from klr.ApprovalRequests where ApprovalId=2)

select closedOn,closebyId,closedReplay,isClosed from klr.ApprovalRequests where ApprovalId=2 
SELECT Requested,LastmodifiedBy,Lastmodified,apporvalmsg,TypesMembership from klr.Userlist 
where id=(select id from klr.ApprovalRequests where ApprovalId=2)


DECLARE @appid int,@rply varchar(100),@closedid int,@mem VARCHAR(30)
set @appid=1
set @rply='Thanks applying welcoming to oursite'
set @closedid=1006
set @mem='owner'
update klr.ApprovalRequests set isClosed=1,closedReplay=@rply
,closebyId=@closedid ,closedOn=getdate() where ApprovalId= @appid
update klr.Userlist set TypesMembership=@mem,apporvalmsg=@rply ,Lastmodified=GETDATE()
,LastmodifiedBy=(Select userName from klr.Userlist where id=@closedid),
Requested=0 where id=(select id from klr.ApprovalRequests where ApprovalId=@appid)


select closedOn,closebyId,closedReplay,isClosed from klr.ApprovalRequests where ApprovalId=1
SELECT Requested,LastmodifiedBy,Lastmodified,apporvalmsg,TypesMembership from klr.Userlist 
where id=(select id from klr.ApprovalRequests where ApprovalId =1)



select * from klr.ApprovalRequests
select * from klr.Userlist as us INNER JOIN klr.ApprovalRequests as ap on us.id=ap.id
select id from klr.ApprovalRequests

set IDENTITY_INSERT SQLTraining.klr.ApprovalRequests on
insert into klr.ApprovalRequests (ProofType,Proof,AttachmentMessage,id) VALUES 
(4,'PASSPORT0000','i m real human being to live in chennai.please approve me!',1007),
(5,'DRILIC000','i m real human being to live in chennai.please approve me!',1006),
(1,'PAN897867','i m real human being to live in chennai.please approve me!',1001),
(1,'PAN111111','i m real human being to live in chennai.please approve me!',1002),
(2,'ADH111111','i m real human being to live in chennai.please approve me!',1003),
(2,'ADHA33333','i m real human being to live in chennai.please approve me!',1001),
(1,'PAN222222','i m real human being to live in chennai.please approve me!',1003),
(3,'VOTER8888','i m real human being to live in chennai.please approve me!',1001),
(1,'PAN444444','i m real human being to live in chennai.please approve me!',1002),
(2,'ADHA444444','i m real human being to live in chennai.please approve me!',1001),
(3,'VOTER00000','i m real human being to live in chennai.please approve me!',1003)


set IDENTITY_INSERT SQLTraining.klr.ApprovalRequests off

















----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
---usertable


truncate TABLE [klr].[Userlist]
set IDENTITY_INSERT SQLTraining.klr.Userlist on

insert into klr.Userlist(id,userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values(1006,'rupeshco1','rupco0@datazoic.com','7075608979','KLR@pesh','appleIShealty','owner')

insert into klr.Userlist(id,userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values(1002,'rupeshco2','rupco1@datazoic.com','7075608979','KLR@pesh','appleIShealty','owner')

insert into klr.Userlist(id,userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values(1003,'rupeshco3','rupco2@datazoic.com','7075608979','KLR@pesh','appleIShealty','owner')

insert into klr.Userlist(id,userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values(1004,'rupeshco4','rupco3@datazoic.com','7075608979','KLR@pesh','appleIShealty','owner')

insert into klr.Userlist(id,userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values(1005,'rupeshco5','rupco4@datazoic.com','7075608979','KLR@pesh','appleIShealty','owner')

insert into klr.Userlist(id,userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values(1001,'rupeshad1','rupa@datazoic.com','7075608979','KLR@pesh','appleIShealty','admin')

insert into klr.Userlist(id,userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values(1007,'rupesh0','rup0@datazoic.com','7075608979','KLR@pesh','appleIShealty','customer')

insert into klr.Userlist(id,userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values(1008,'rupesh1','rup1@datazoic.com','7075608979','KLR@pesh','appleIShealty','customer')

insert into klr.Userlist(id,userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values(1009,'rupesh2','rup2@datazoic.com','7075608979','KLR@pesh','appleIShealty','customer')

set IDENTITY_INSERT SQLTraining.klr.Userlist off


insert into klr.Userlist(userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values('rupesh2','rup2@datazoic.com','7075608979','KLR@pesh','appleIShealty','customer')


SELECT * FROM klr.Userlist where isDelete=1
SELECT * FROM klr.Userlist where isDelete=1

update  klr.Hotels set Htisdeleted=0 where Htisdeleted=1
update  klr.Hotels set HTstatus=1  where isDelete=1

select * from klr.Hotels WHERE  Htisdeleted=0 and HTstatus=0  and id=1005


insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation,Htimage) VALUES(?,?,?,?, ?,0,?,?)


----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
--hotels


set IDENTITY_INSERT SQLTraining.klr.Hotels on

insert into klr.Hotels  (Hotelid,id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation,Htimage) VALUEs

(1,1001,'Malika','6.no.door,pergudi,chennai(600512)','9898987654','this located in chennai',5,'chennai','hotel1Roundcurvl.jpg')

insert into klr.Hotels  (Hotelid,id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation,Htimage) VALUEs
(2,1001,'kings','666.no.door,pergudi,chennai(600512)','9898987654','this located in chennai',5,'chennai','hotel2island.jpg')

insert into klr.Hotels  (Hotelid,id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation,Htimage) VALUEs
(3,1002,'prabha grand inn','6.no.door,pergudi,chittoor(517001)','9898987654','this located in chittoor',5,'chittoor','hotel3publicmodel.jpg')

insert into klr.Hotels  (Hotelid,id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation,Htimage) VALUEs
(4,1003,'gem minners','06\8.no.door,pergudi,tirupati(512892)','9898987654','this located in tirupati',5,'tirupati','hotel4costtly.jpg')

insert into klr.Hotels  (Hotelid,id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation,Htimage) VALUEs
(5,1003,'gem beggers','99.no.door,pergudi,tirupati(512892)','9898987654','this located in tirupati',5,'tirupati','hotel5royal.jpg')

insert into klr.Hotels  (Hotelid,id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation,Htimage) VALUEs
(6,1005,'golden hotel','6.no.door,pergudi,chennai(600512)','9898987654','this located in chennai',5,'chennai','hotel6swingpool.jpg')

insert into klr.Hotels  (Hotelid,id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation,Htimage) VALUEs
(7,1006,'Mui000','16-67/5.no.door,pergudi,chennai(600512)','9898987654','this located in chennai',5,'chennai','hotel7_4blocks.jpg')

insert into klr.Hotels  (Hotelid,id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation,Htimage) VALUEs
(8,1006,'goodness','17-89/3,door,pergudi,tirupati(600512)','9898987654','this located in tirupati',5,'tirupati','hotel1Roundcurvl.jpg')

insert into klr.Hotels  (Hotelid,id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation,Htimage) VALUEs
(9,1006,'lovers flyer','76.no.door,pergudi,chittoor(600512)','9898987654','this located in chittoor',5,'chittoor','hotel4costtly.jpg')

set IDENTITY_INSERT SQLTraining.klr.Hotels off



----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
-- roomlist

truncate TABLE [klr].[Roomlists]

set IDENTITY_INSERT SQLTraining.klr.Roomlists on

insert into klr.Roomlists (HostelId, id, RoomType, BedCount, price , CanStaycount ,Rmcode)VALUES(?,? ,?,? ?,? ?)
(1,1001,'Ac',3, 1700.8,5,8)
insert into klr.Roomlists (HostelId, id, RoomType, BedCount, price , CanStaycount )VALUES
(1,1001,'Ac',3, 1700.8,5), (1,1001,'Ac',3, 1700.8,5), (1,1001,'Ac',3, 1700.8,5), (1,1001,'Non/Ac',3,750.80,7), (1,1001,'Non/Ac',3,750.80,7),
(2,1001,'Ac',3, 1500.8,3), (2,1001,'Ac',3, 1500.8,3), (2,1001,'Ac',3, 1500.8,3), (2,1001,'Non/Ac',3,750.80,7), (2,1001,'Non/Ac',3,750.80,7),
(3,1002,'Ac',3, 1500.8,3), (3,1002,'Ac',3, 1500.8,3), (3,1002,'Ac',3, 1700.8,5), (3,1002,'Ac',3, 1500.8,3), (3,1002,'Ac',3, 1700.8,5),
(4,1003,'Ac',3, 1500.8,3), (4,1003,'Ac',3, 1500.8,3), (4,1003,'Ac',3, 1700.8,5), (4,1003,'Ac',3, 1500.8,3), (4,1003,'Ac',3, 1700.8,5),
(5,1003,'Ac',3, 1500.8,3), (5,1003,'Ac',3, 1500.8,3), (5,1003,'Ac',3, 1700.8,5),
(6,1005,'Ac',3, 1500.8,3), (6,1005,'Ac',3, 1700.8,5),
(7,1006,'Ac',3, 1700.8,5), (7,1006,'Ac',3, 1500.8,3), (7,1006,'Non/Ac',3,750.80,7),
(8,1006,'Ac',3, 1500.8,3), (8,1006,'Non/Ac',3,750.80,3),
(9,1006,'Ac',3, 1500.8,3)

set IDENTITY_INSERT SQLTraining.klr.Roomlists off

select MAx(price) as max from klr.Roomlists where HostelId=1 
update  klr.Roomlists set RmisDeleted=1,LastmodifiedByRM='me',LastmodifiedRM=GETDATE()  WHERE HostelId=6
select * from klr.Roomlists
DECLARE @hotelid as int=1
--select *from klr.Hotels where Hotelid=@hotelid
update klr.Hotels set StaringPr=
(Select MIN(price)from klr.Roomlists where HostelId=@hotelid )
 where  Hotelid=@hotelid
update klr.Hotels set EndPr=(Select MAx(price)from klr.Roomlists where HostelId=@hotelid )  where  Hotelid=@hotelid
--select *from klr.Hotels where Hotelid=@hotelid

update  klr.Roomlists RoomType=?, BedCount=?, price=? , CanStaycount=?     ,Rmcode=? where Roomid=?
DECLARE @hotelid2 as int=2 
--select *from klr.Hotels where Hotelid=@hotelid2
update klr.Hotels set StaringPr=(Select MIN(price)from klr.Roomlists where HostelId=@hotelid2 ) where  Hotelid=@hotelid2
update klr.Hotels set EndPr=(Select MAx(price)from klr.Roomlists where HostelId=@hotelid2 ) where  Hotelid=@hotelid2
--select *from klr.Hotels where Hotelid=@hotelid2

DECLARE @hotelid3 as int=3
--select *from klr.Hotels where Hotelid=@hotelid3
update klr.Hotels set StaringPr=(Select MIN(price)from klr.Roomlists where HostelId=@hotelid3 ) where  Hotelid=@hotelid3
update klr.Hotels set EndPr=(Select MAx(price)from klr.Roomlists where HostelId=@hotelid3 ) where  Hotelid=@hotelid3
--select *from klr.Hotels where Hotelid=@hotelid3

DECLARE @hotelid4 as int=4
--select *from klr.Hotels where Hotelid=@hotelid4
update klr.Hotels set StaringPr=(Select MIN(price)from klr.Roomlists where HostelId=@hotelid4 ) where  Hotelid=@hotelid4
update klr.Hotels set EndPr=(Select MAx(price)from klr.Roomlists where HostelId=@hotelid4 ) where  Hotelid=@hotelid4
--select *from klr.Hotels where Hotelid=@hotelid4

DECLARE @hotelid5 as int=5
--select *from klr.Hotels where Hotelid=@hotelid5
update klr.Hotels set StaringPr=(Select MIN(price)from klr.Roomlists where HostelId=@hotelid5 ) where  Hotelid=@hotelid5
update klr.Hotels set EndPr=(Select MAx(price)from klr.Roomlists where HostelId=@hotelid5 ) where  Hotelid=@hotelid5
--select *from klr.Hotels where Hotelid=@hotelid5

DECLARE @hotelid6 as int=6
--select *from klr.Hotels where Hotelid=@hotelid6
update klr.Hotels set StaringPr=(Select MIN(price)from klr.Roomlists where HostelId=@hotelid6 ) where  Hotelid=@hotelid6
update klr.Hotels set EndPr=(Select MAx(price)from klr.Roomlists where HostelId=@hotelid6 ) where  Hotelid=@hotelid6
--select *from klr.Hotels where Hotelid=@hotelid6

DECLARE @hotelid7 as int=7
--select *from klr.Hotels where Hotelid=@hotelid7
update klr.Hotels set StaringPr=(Select MIN(price)from klr.Roomlists where HostelId=@hotelid7 ) where  Hotelid=@hotelid7
update klr.Hotels set EndPr=(Select MAx(price)from klr.Roomlists where HostelId=@hotelid7 ) where  Hotelid=@hotelid7
--select *from klr.Hotels where Hotelid=@hotelid7

DECLARE @hotelid8 as int=8
--select *from klr.Hotels where Hotelid=@hotelid8
update klr.Hotels set StaringPr=(Select MIN(price)from klr.Roomlists where HostelId=@hotelid8 ) where  Hotelid=@hotelid8
update klr.Hotels set EndPr=(Select MAx(price)from klr.Roomlists where HostelId=@hotelid8 ) where  Hotelid=@hotelid8
--select *from klr.Hotels where Hotelid=@hotelid8

DECLARE @hotelid9 as int=9
--select *from klr.Hotels where Hotelid=@hotelid9
update klr.Hotels set StaringPr=(Select MIN(price)from klr.Roomlists where HostelId=@hotelid9 )  where  Hotelid=@hotelid9
update klr.Hotels set EndPr=(Select MAx(price)from klr.Roomlists where HostelId=@hotelid9 ) where  Hotelid=@hotelid9

--select *from klr.Roomlists where Hotelid=@hotelid9
select 1



----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
--BookingList


set IDENTITY_INSERT SQLTraining.klr.BookingLists on


insert into klr.BookingLists (id,Hostelid,Roomid,NoOfGuests,BookedDate,BookingFrom,BookingTo,TotalStay) VALUES
(1001,2,8,3,getdate(),getdate(),convert(date, DATEADD(day,3,getdate()), 105),3)


set IDENTITY_INSERT SQLTraining.klr.BookingLists off











