
-- selecting user is our database and cretantion to check match are not

--select id,userEmail,userPassword from klr.Userlist where isDelete=0	and  TypesMembership='admin'
select id,userEmail,userPassword,TypesMembership from klr.Userlist where isDelete=0	and  TypesMembership='admin' and userEmail='rupa@datazoic.com' 



insert into klr.Userlist(userName,userEmail,userPhoneNo,userPassword,SecertKey,TypesMembership)
values('rupesh0','rup0@datazoic.com','7075608979','KLR@pesh','appleIShealty','owner')

-- select query to check all active user in table 
select * from klr.ApprovalRequests where  id=1006
select * from klr.Userlist where isDelete=0 and id= 1006

select * from klr.Userlist where isDelete=0 and TypesMembership='owner'--admin

select id,userEmail,userPassword,TypesMembership from klr.Userlist
select * from klr.Userlist
where isDelete=0	and  TypesMembership='admin' and userEmail='rupa@datazoic.com' --any user

update klr.Userlist set isDelete=0 

select * from klr.Userlist where id=1001 -- user object creating


update klr.Userlist set userPassword='KLR@pesh' where userEmail='rk.gmail.com' and SecertKey='appleIShealty'
update klr.Userlist set userPassword='KLR@pesh0' where userEmail='rk.gmail.com' and userPassword='KLR@pesh'




update  klr.Userlist set Requested=1 and apporvalmsg="approvel me"
insert into klr.ApprovalRequests (ProofType,Proof,AttachmentMessage,id) VALUES 
(1,'PAN897867','i m real human being to live in chennai.please approve me!',1001)
--user action to make request to approval to make him as owner
---                         Attachementmessage and apporvalmsg are going to be same 
update klr.Userlist set userPassword ='' where id =1001 and Secerkey=''
update klr.Userlist set userPassword ='' where id =1001 and userPassword=''
-- for updateing user password 1 2


----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
-- select hotellist
insert into klr.Hotels  (id,HostelName,HotelAddress,HotelNumber,HotelDesc,Rating,HotelLocation) VALUEs
(1001,'Malika','6.no.door,pergudi,chennai(600512)','9898987654','this located in chennai',5,'chennai')
--intially its was 

select * from klr.Hotels WHERE Htisdeleted=0  and HTstatus=1 and Rating=0
-- select hotellist
select * from klr.Hotels WHERE Htisdeleted=0
--used for get all owner admin list
select top(3)* from klr.Hotels   with (NOLOCK) WHERE Htisdeleted=0   and HTstatus=1  and rating=5  ORDER by EndPr asc

SELECT * FROM klr.Hotels  with (NOLOCK)  WHERE Htisdeleted = 0  and HTstatus=1 AND  Rating>5 and Rating<7
select* from klr.Hotels   with (NOLOCK)  WHERE Htisdeleted=0 and Rating<>0 ORDER by Rating
HotelLocation ='' and Rating>0 and Rating<5 and StaringPr>0 and EndPr<99999
--- used for searching of query

select * from klr.Hotels  where Htisdeleted=0 and id=1001
--used for get all owner hotels/admin list

select * from klr.Hotels where Htisdeleted=0 and Htstatus =0
UPDATE klr.hotels set Htstatus=1 and LastmodifiedHt=GETDATE() and LastmodifiedByHt='admina' where Hotelid=1
--htstatus 0-request 1-approved( 2-rejected and  htidelected is automatically triigered ) NULL--only registered
--approval request is being mangaged
--admin table changes 

update klr.Hotels set Htisdeleted =1 where Hotelid=1
-- update klr.Hotels set 1,1,1, where Hotelid=1
--  update any field in hotellists

select StaringPr,EndPr from Klr.Hotels where Hotelid=2


----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------

-- select roomlists 
insert into klr.Roomlists (HostelId, id, RoomType, BedCount, price , CanStaycount )VALUES
    (1,1001,'Ac',3, 1700.8,5)
select StartPr,EndPr from Klr.Hotels where Hotelid=2
update klr.Hotels set StartPr(or)EndPr=1700.8
-- select roomlists 

SELECT * from klr.Roomlists WHERE RmisDeleted=0

SELECT * from klr.Roomlists WHERE RmisDeleted=0   and HostelId =1

SELECT * from klr.Roomlists WHERE RmisDeleted=0 and id=1001
-- owner his roomlisted 
SELECT * from klr.Roomlists WHERE RmisDeleted=0 and HotelId=1
-- to show room in a hotel to user to book
UPDATE klr.Roomlists set RmisDeleted =1 where Roomid=1
-- update set ,,,,, where Roomid=1
--used to change room details by ownere

----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
-- approvallist table 

insert into klr.ApprovalRequests (ProofType,Proof,AttachmentMessage,id) VALUES 
(1,'PAN897867','i m real human being to live in chennai.please approve me!',1001)

--- select approval requests newly cvreated by mee

select * from klr.ApprovalRequests where isClosed=0;


select * from klr.ApprovalRequests where isClosed=0 and id=1006

update klr.ApprovalRequests set AttachmentMessage='' and isClosed=1 and closedReplay='user'
closedOn='' where ApprovalIddf=1
update klr.Userlist set Requested=1 and apporvalmsg='ff' and TypesMembership='' and
Lastmodified=date and LastmodifiedBy='' where id=1001

----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------

--booing list table

insert into klr.BookingLists 
(id,Hostelid,Roomid,NoOfGuests,BookedDate,BookingFrom,BookingTo,TotalStay) VALUES
(1008,2,8, 3,getdate(),getdate(), DATEADD(day,3,getdate()),3)

select * from klr.BookingLists where BookingisClosed=0
insert into klr.BookingLists 
(id,Hostelid,Roomid,NoOfGuests,BookedDate,BookingFrom,BookingTo,TotalStay) VALUES
(1008,2,8, 3,getdate(),DATEADD(day,1,'2023-07-04'), DATEADD(day,2,'2023-07-04'),3)



select BookingFrom from  [klr].[BookingLists] where  BookingisClosed=0 and BookingFrom >GETDATE() and Roomid=11

select * from  [klr].[BookingLists] where  BookingFrom <GETDATE()




select * from klr.BookingLists where BookingisClosed=0

select BookingFrom from klr.BookingLists where BookingisClosed=0 and Roomid=8

select DATE_FORMAT(BookingFrom, '%Y %m %d') from klr.BookingLists where BookingisClosed=0 and Roomid=8

insert into klr.BookingLists (id,Hostelid,Roomid,NoOfGuests,BookedDate,BookingFrom,BookingTo,TotalStay) VALUES(?,?,?, ?,getdate(),DATEADD(day,?,?), DATEADD(day,?,?),1)


(id,Hostelid,Roomid,NoOfGuests,BookedDate,BookingFrom,BookingTo,TotalStay) VALUES
(1008,2,8, 3,getdate(),getdate(), DATEADD(day,3,getdate()),1)

select * from klr.BookingLists where BookingisClosed=0 and id=1006
--- select booking that for user and hotel and which room 
select * from klr.BookingLists where BookingisClosed=0
-- to show to user 
select bl.id,bl.Hostelid,bl.Roomid,bl.NoOfGuests,CONVERT(varchar(100),bl.BookedDate,105)as bookedon,CONVERT(varchar(100),bl.BookingFrom,105) as BookingFrom,
CONVERT(varchar(100),bl.BookingTo,105) as BookingTo,bl.TotalStay,bl.Totalprice,rl.RoomType,rl.price,hl.HostelName,hl.HotelLocation,hl.HotelAddress,hl.HotelNumber,ul.userEmail,ul.userName,ul.userPhoneNo
from klr.BookingLists as bl inner JOIN  klr.Roomlists as rl on bl.Roomid=rl.Roomid
inner JOIN  klr.Hotels as hl on hl.Hotelid=rl.HostelId inner JOIN  klr.Userlist as ul on ul.id=bl.id where bl.BookingID=2

select COUNT(*) FROM klr.Hotels where id=1006
select * from klr.Hotels WHERE Htisdeleted=0 and HTstatus=1 and id=1006
select HostelName,HTstatus FROM klr.Hotels where id=1006
select COUNT(*) FROM klr.BookingLists

update klr.Hotels set Htisdeleted=1 where Hotelid=1002

select * FROM klr.Hotels where HTstatus=0

select  StaringPr,EndPr,HostelName from klr.Hotels  where Hotelid=9  
Select MAx(price)as max,MIN(price)from klr.Roomlists 
Select MIN(price)from klr.Roomlists where HostelId=9


SELECT * from klr.Roomlists WITH (NOLOCK) WHERE RmisDeleted=0   and id =1006 ORDER BY HostelId
select * FROM klr.Userlist WHERE id=1008
select COUNT(*) FROM klr.ApprovalRequests

select * from klr.BookingLists where BookingisClosed=0 and id=1006
select * from klr.BookingLists where BookingisClosed=0 and id=1001

select BookedDate,BookingFrom,BookingTo from klr.BookingLists where Roomid=1
select BookedDate,BookingFrom,BookingTo from klr.BookingLists where BookingFrom=GETDATE()

update klr.BookingLists set BookingisClosed=1 and LastmodifiedBy='owner' and Lastmodified=date
where BookingId=1