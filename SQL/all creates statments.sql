
CREATE TABLE [klr].[ApprovalRequests](
	[ApprovalId] [int] IDENTITY(1,1) NOT NULL,
	[ProofType] [int] NULL default 0,
	[Proof] [varchar](20) NULL default 'NTH',
	[AttachmentMessage] [varchar](max) NULL default 'NTH',
	[id] [int] NOT NULL,
	[closebyId] [int] NULL default 1000,
	[isClosed] [int] NULL default 0,
	[closedReplay] [varchar](150) NULL default 'NTH',
	[closedOn] [date] NULL DEFAULT GETDATE()
)
CREATE TABLE [klr].[BookingLists](
	[BookingId] [int] IDENTITY(1,1) NOT NULL,
	[id] [int] NOT NULL,
	[Hostelid] [int] NOT NULL,
	[Roomid] [int] NOT NULL,
	[NoOfGuests] [int] NOT NULL,
	[BookedDate] [date] NULL default GETDATE(),
	[BookingFrom] [date] not NULL,
	[BookingTo] [date] not NULL,
	[TotalStay] [int] NULL default 1,    
    Totalprice [FLOAT]  NULL default 500,
	[StayDetails] [int] NULL default 0,
	[BookingisClosed] [int] NULL default 0,
	[Lastmodified] [date] NULL default GETDATE(),
	[LastmodifiedBy] [varchar](30) NULL DEFAULT '-'
) 

CREATE TABLE [klr].[Userlist](
	[id] [int] IDENTITY(1001,1) NOT NULL,
	[userName] [varchar](30) NOT NULL,
	[userEmail] [varchar](50) NOT NULL,
	[userPhoneNo] [varchar](10) NOT NULL,
	[userPassword] [varchar](40) NOT NULL,
	[SecertKey] [varchar](20) NOT NULL,
	photoRef varchar(100) Null default 'NTH',
	[Requested] [int] NULL default 0,    
    apporvalmsg [varchar](100) NULL default 'NTH',
	[TypesMembership] [varchar](10) NULL DEFAULT 'customer',
	[isDelete] [int] NULL default 0,
	[Lastmodified] [date] NULL default GETDATE(),
	[LastmodifiedBy] [varchar](30) NULL DEFAULT 'admin'
) 

CREATE TABLE [klr].[Hotels](
	[Hotelid] [int] IDENTITY(1,1) NOT NULL,
	[id] [int] NOT NULL,
	[HostelName] [varchar](50) NOT NULL,
	[HotelAddress] [varchar](100) NOT NULL,
	[HotelNumber] [varchar](10) NOT NULL,
	[HotelDesc] [varchar](300) NULL default 'We will give best services!',
	[Htimage] [varchar](500) NULL default '../assets/img/hotels/hotel1Roundcurvl.jpg',
	[Rating] [int] NULL default 5,
	[HotelLocation] [varchar](50) not NULL,
	[StaringPr] [float] NULL default 0,
	[EndPr] [float] NULL default 0,
	[HTstatus] [int] NULL default 0,--- default 0
	[statusmsg] [varchar](100) NULL default 'NTH',---
	[Htisdeleted] [int] NULL default 0,
	[LastmodifiedHT] [date] NULL  default GETDATE(),
	[LastmodifiedByHT] [varchar](30) NULL  default 'admin'
) 

CREATE TABLE [klr].[Roomlists](
	[Roomid] [int] IDENTITY(1,1) NOT NULL,
	[HostelId] [int] NOT NULL,
	[id] [int] NOT NULL,
	[RoomType] [varchar](50) NULL default 'Non A/C & SingleBed',
	[BedCount] [int] NOT NULL,
	[roomImage] VARCHAR(300) null DEFAULT '../assets/img/rooms/room1whiteice.webp',
	[price] [float] NOT NULL ,
	[Rmcode] [varchar](5) NULL  default '-',
	[CanStaycount] [int] NOT NULL,
	[RmisDeleted] [int] NULL default 0,
	[LastmodifiedRM] [date] NULL  default GETDATE(),
	[LastmodifiedByRM] [varchar](30) NULL  default 'admin'
) 

drop TABLE [klr].[BookingLists]
drop TABLE [klr].[Userlist]
drop TABLE [klr].[ApprovalRequests]
drop TABLE [klr].[Roomlists]
drop TABLE [klr].[Hotels] 

truncate TABLE [klr].[BookingLists]
truncate TABLE [klr].[Userlist]
truncate TABLE [klr].[ApprovalRequests]
truncate TABLE [klr].[Roomlists]
truncate TABLE [klr].[Hotels]


select * from  [klr].[Userlist]
select * from  [klr].[BookingLists] 
select * from  [klr].[ApprovalRequests]
select * from  [klr].[Roomlists]
select * from  [klr].[Hotels]

select COUNT(*) from  [klr].[Roomlists]


set IDENTITY_INSERT SQLTraining.klr.Userlist on
set IDENTITY_INSERT SQLTraining.klr.BookingLists on
set IDENTITY_INSERT SQLTraining.klr.ApprovalRequests on
set IDENTITY_INSERT SQLTraining.klr.Roomlists on
set IDENTITY_INSERT SQLTraining.klr.Hotels on


set IDENTITY_INSERT SQLTraining.klr.Userlist off
set IDENTITY_INSERT SQLTraining.klr.BookingLists off
set IDENTITY_INSERT SQLTraining.klr.ApprovalRequests off
set IDENTITY_INSERT SQLTraining.klr.Roomlists off
set IDENTITY_INSERT SQLTraining.klr.Hotels off