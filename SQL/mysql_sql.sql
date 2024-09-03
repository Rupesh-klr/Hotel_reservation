CREATE TABLE `klr`.`ApprovalRequests`(
    `ApprovalId` int AUTO_INCREMENT PRIMARY KEY,
    `ProofType` int NULL DEFAULT 0,
    `Proof` varchar(20) NULL DEFAULT 'NTH',
    `AttachmentMessage` varchar(255) NULL DEFAULT 'NTH',
    `id` int NOT NULL,
    `closebyId` int NULL DEFAULT 1000,
    `isClosed` int NULL DEFAULT 0,
    `closedReplay` varchar(150) NULL DEFAULT 'NTH',
    `closedOn` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `klr`.`BookingLists`(
    `BookingId` int AUTO_INCREMENT PRIMARY KEY,
    `id` int NOT NULL,
    `Hostelid` int NOT NULL,
    `Roomid` int NOT NULL,
    `NoOfGuests` int NOT NULL,
    `BookedDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `BookingFrom` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `BookingTo` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `TotalStay` int NULL DEFAULT 1,
    `Totalprice` bigint NULL DEFAULT 500,
    `StayDetails` int NULL DEFAULT 0,
    `BookingisClosed` int NULL DEFAULT 0,
    `Lastmodified` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `LastmodifiedBy` varchar(30) NULL DEFAULT '-'
);

CREATE TABLE `klr`.`Userlist`(
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `userName` varchar(30) NOT NULL,
    `userEmail` varchar(50) NOT NULL,
    `userPhoneNo` varchar(10) NOT NULL,
    `userPassword` varchar(40) NOT NULL,
    `SecertKey` varchar(20) NOT NULL,
    `photoRef` varchar(100) NULL DEFAULT 'NTH',
    `Requested` int NULL DEFAULT 0,
    `apporvalmsg` varchar(100) NULL DEFAULT 'NTH',
    `TypesMembership` varchar(10) NULL DEFAULT 'customer',
    `isDelete` int NULL DEFAULT 0,
    `Lastmodified` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `LastmodifiedBy` varchar(30) NULL DEFAULT 'admin'
);

CREATE TABLE `klr`.`Hotels`(
    `Hotelid` int AUTO_INCREMENT PRIMARY KEY,
    `id` int NOT NULL,
    `HostelName` varchar(50) NOT NULL,
    `HotelAddress` varchar(100) NOT NULL,
    `HotelNumber` varchar(10) NOT NULL,
    `HotelDesc` varchar(300) NULL DEFAULT 'We will give best services!',
    `Htimage` varchar(500) NULL DEFAULT '../assets/img/hotels/hotel1Roundcurvl.jpg',
    `Rating` int NULL DEFAULT 5,
    `HotelLocation` varchar(50) NOT NULL,
    `StaringPr` bigint NULL DEFAULT 0,
    `EndPr` bigint NULL DEFAULT 0,
    `HTstatus` int NULL DEFAULT 0,
    `statusmsg` varchar(100) NULL DEFAULT 'NTH',
    `Htisdeleted` int NULL DEFAULT 0,
    `LastmodifiedHT` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `LastmodifiedByHT` varchar(30) NULL DEFAULT 'admin'
);

CREATE TABLE `klr`.`Roomlists`(
    `Roomid` int AUTO_INCREMENT PRIMARY KEY,
    `HostelId` int NOT NULL,
    `id` int NOT NULL,
    `RoomType` varchar(50) NULL DEFAULT 'Non A/C & SingleBed',
    `BedCount` int NOT NULL,
    `roomImage` varchar(300) NULL DEFAULT '../assets/img/rooms/room1whiteice.webp',
    `price` bigint NOT NULL,
    `Rmcode` varchar(5) NULL DEFAULT '-',
    `CanStaycount` int NOT NULL,
    `RmisDeleted` int NULL DEFAULT 0,
    `LastmodifiedRM` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `LastmodifiedByRM` varchar(30) NULL DEFAULT 'admin'
);

------------
-- ApprovalRequests

-- 1-pan 2-adhar 3-voter 4-passport 5-driving license

-- Making 1006 and 1007 as requesting to become as 
SET FOREIGN_KEY_CHECKS=0;

INSERT INTO klr.ApprovalRequests (ApprovalId, ProofType, Proof, AttachmentMessage, id, isClosed) VALUES 
(1, 1, 'PAN897867', 'I am a real human being to live in Chennai. Please approve me!', 1007, 0),
(2, 1, 'PAN111111', 'I am a real human being to live in Chennai. Please approve me!', 1008, 0);

SET FOREIGN_KEY_CHECKS=1;

UPDATE klr.Userlist SET Requested=1, apporvalmsg='?' WHERE id=?;
UPDATE klr.Userlist SET Requested=2, apporvalmsg='I am a real human being to live in Chennai. Please approve me to become an admin!' WHERE id=1008;

SELECT * FROM klr.ApprovalRequests WHERE isClosed=0;

UPDATE klr.ApprovalRequests SET isClosed=1, closedReplay='Thanks for applying, welcoming to our site', closebyId=1006, closedOn=NOW() WHERE ApprovalId=2;
UPDATE klr.Userlist SET TypesMembership='admin', apporvalmsg='Thanks for applying, welcoming to our site', Lastmodified=NOW(), LastmodifiedBy=(SELECT userName FROM klr.Userlist WHERE id=1006), Requested=0 WHERE id=(SELECT id FROM klr.ApprovalRequests WHERE ApprovalId=2);

SELECT closedOn, closebyId, closedReplay, isClosed FROM klr.ApprovalRequests WHERE ApprovalId=2;
SELECT Requested, LastmodifiedBy, Lastmodified, apporvalmsg, TypesMembership FROM klr.Userlist WHERE id=(SELECT id FROM klr.ApprovalRequests WHERE ApprovalId=2);

SET @appid = 1;
SET @rply = 'Thanks for applying, welcoming to our site';
SET @closedid = 1006;
SET @mem = 'owner';

UPDATE klr.ApprovalRequests SET isClosed=1, closedReplay=@rply, closebyId=@closedid, closedOn=NOW() WHERE ApprovalId=@appid;
UPDATE klr.Userlist SET TypesMembership=@mem, apporvalmsg=@rply, Lastmodified=NOW(), LastmodifiedBy=(SELECT userName FROM klr.Userlist WHERE id=@closedid), Requested=0 WHERE id=(SELECT id FROM klr.ApprovalRequests WHERE ApprovalId=@appid);

SELECT closedOn, closebyId, closedReplay, isClosed FROM klr.ApprovalRequests WHERE ApprovalId=1;
SELECT Requested, LastmodifiedBy, Lastmodified, apporvalmsg, TypesMembership FROM klr.Userlist WHERE id=(SELECT id FROM klr.ApprovalRequests WHERE ApprovalId=1);

SELECT * FROM klr.ApprovalRequests;
SELECT * FROM klr.Userlist AS us INNER JOIN klr.ApprovalRequests AS ap ON us.id=ap.id;
SELECT id FROM klr.ApprovalRequests;

SET FOREIGN_KEY_CHECKS=0;

INSERT INTO klr.ApprovalRequests (ProofType, Proof, AttachmentMessage, id) VALUES 
(4, 'PASSPORT0000', 'I am a real human being to live in Chennai. Please approve me!', 1007),
(5, 'DRILIC000', 'I am a real human being to live in Chennai. Please approve me!', 1006),
(1, 'PAN897867', 'I am a real human being to live in Chennai. Please approve me!', 1001),
(1, 'PAN111111', 'I am a real human being to live in Chennai. Please approve me!', 1002),
(2, 'ADH111111', 'I am a real human being to live in Chennai. Please approve me!', 1003),
(2, 'ADHA33333', 'I am a real human being to live in Chennai. Please approve me!', 1001),
(1, 'PAN222222', 'I am a real human being to live in Chennai. Please approve me!', 1003),
(3, 'VOTER8888', 'I am a real human being to live in Chennai. Please approve me!', 1001),
(1, 'PAN444444', 'I am a real human being to live in Chennai. Please approve me!', 1002),
(2, 'ADHA444444', 'I am a real human being to live in Chennai. Please approve me!', 1001),
(3, 'VOTER00000', 'I am a real human being to live in Chennai. Please approve me!', 1003);

SET FOREIGN_KEY_CHECKS=1;

-- Userlist table

TRUNCATE TABLE klr.Userlist;

SET FOREIGN_KEY_CHECKS=0;

INSERT INTO klr.Userlist (id, userName, userEmail, userPhoneNo, userPassword, SecertKey, TypesMembership) VALUES
(1006, 'rupeshco1', 'rupco0@datazoic.com', '7075608979', 'KLR@pesh', 'appleIShealty', 'owner'),
(1002, 'rupeshco2', 'rupco1@datazoic.com', '7075608979', 'KLR@pesh', 'appleIShealty', 'owner'),
(1003, 'rupeshco3', 'rupco2@datazoic.com', '7075608979', 'KLR@pesh', 'appleIShealty', 'owner'),
(1004, 'rupeshco4', 'rupco3@datazoic.com', '7075608979', 'KLR@pesh', 'appleIShealty', 'owner'),
(1005, 'rupeshco5', 'rupco4@datazoic.com', '7075608979', 'KLR@pesh', 'appleIShealty', 'owner'),
(1001, 'rupeshad1', 'rupa@datazoic.com', '7075608979', 'KLR@pesh', 'appleIShealty', 'admin'),
(1007, 'rupesh0', 'rup0@datazoic.com', '7075608979', 'KLR@pesh', 'appleIShealty', 'customer'),
(1008, 'rupesh1', 'rup1@datazoic.com', '7075608979', 'KLR@pesh', 'appleIShealty', 'customer'),
(1009, 'rupesh2', 'rup2@datazoic.com', '7075608979', 'KLR@pesh', 'appleIShealty', 'customer');

SET FOREIGN_KEY_CHECKS=1;

INSERT INTO klr.Userlist (userName, userEmail, userPhoneNo, userPassword, SecertKey, TypesMembership) VALUES
('rupesh2', 'rup2@datazoic.com', '7075608979', 'KLR@pesh', 'appleIShealty', 'customer');

SELECT * FROM klr.Userlist WHERE isDelete=1;
SELECT * FROM klr.Userlist WHERE isDelete=1;

UPDATE klr.Hotels SET Htisdeleted=0 WHERE Htisdeleted=1;
UPDATE klr.Hotels SET HTstatus=1 WHERE isDelete=1;

SELECT * FROM klr.Hotels WHERE Htisdeleted=0 AND HTstatus=0 AND id=1005;

INSERT INTO klr.Hotels (id, HostelName, HotelAddress, HotelNumber, HotelDesc, Rating, HotelLocation, Htimage) VALUES
(?, ?, ?, ?, ?, 0, ?, ?);
-- Hotels table

SET FOREIGN_KEY_CHECKS=0;

INSERT INTO klr.Hotels (Hotelid, id, HostelName, HotelAddress, HotelNumber, HotelDesc, Rating, HotelLocation, Htimage) VALUES
(1, 1001, 'Malika', '6.no.door,pergudi,chennai(600512)', '9898987654', 'this located in chennai', 5, 'chennai', 'hotel1Roundcurvl.jpg'),
(2, 1001, 'kings', '666.no.door,pergudi,chennai(600512)', '9898987654', 'this located in chennai', 5, 'chennai', 'hotel2island.jpg'),
(3, 1002, 'prabha grand inn', '6.no.door,pergudi,chittoor(517001)', '9898987654', 'this located in chittoor', 5, 'chittoor', 'hotel3publicmodel.jpg'),
(4, 1003, 'gem minners', '06\\8.no.door,pergudi,tirupati(512892)', '9898987654', 'this located in tirupati', 5, 'tirupati', 'hotel4costtly.jpg'),
(5, 1003, 'gem beggers', '99.no.door,pergudi,tirupati(512892)', '9898987654', 'this located in tirupati', 5, 'tirupati', 'hotel5royal.jpg'),
(6, 1005, 'golden hotel', '6.no.door,pergudi,chennai(600512)', '9898987654', 'this located in chennai', 5, 'chennai', 'hotel6swingpool.jpg'),
(7, 1006, 'Mui000', '16-67/5.no.door,pergudi,chennai(600512)', '9898987654', 'this located in chennai', 5, 'chennai', 'hotel7_4blocks.jpg'),
(8, 1006, 'goodness', '17-89/3,door,pergudi,tirupati(600512)', '9898987654', 'this located in tirupati', 5, 'tirupati', 'hotel1Roundcurvl.jpg'),
(9, 1006, 'lovers flyer', '76.no.door,pergudi,chittoor(600512)', '9898987654', 'this located in chittoor', 5, 'chittoor', 'hotel4costtly.jpg');

SET FOREIGN_KEY_CHECKS=1;

-- Roomlists table

TRUNCATE TABLE klr.Roomlists;

-- Disable foreign key checks to allow identity insert
SET FOREIGN_KEY_CHECKS=0;

-- Insert data into Roomlists table
INSERT INTO klr.Roomlists (HostelId, id, RoomType, BedCount, price, CanStaycount, Rmcode) VALUES
(1, 1001, 'Ac', 3, 1700.8, 5, 8),
(1, 1001, 'Ac', 3, 1700.8, 5, NULL),
(1, 1001, 'Ac', 3, 1700.8, 5, NULL),
(1, 1001, 'Ac', 3, 1700.8, 5, NULL),
(1, 1001, 'Non/Ac', 3, 750.80, 7, NULL),
(1, 1001, 'Non/Ac', 3, 750.80, 7, NULL),
(2, 1001, 'Ac', 3, 1500.8, 3, NULL),
(2, 1001, 'Ac', 3, 1500.8, 3, NULL),
(2, 1001, 'Ac', 3, 1500.8, 3, NULL),
(2, 1001, 'Non/Ac', 3, 750.80, 7, NULL),
(2, 1001, 'Non/Ac', 3, 750.80, 7, NULL),
(3, 1002, 'Ac', 3, 1500.8, 3, NULL),
(3, 1002, 'Ac', 3, 1500.8, 3, NULL),
(3, 1002, 'Ac', 3, 1700.8, 5, NULL),
(3, 1002, 'Ac', 3, 1500.8, 3, NULL),
(3, 1002, 'Ac', 3, 1700.8, 5, NULL),
(4, 1003, 'Ac', 3, 1500.8, 3, NULL),
(4, 1003, 'Ac', 3, 1500.8, 3, NULL),
(4, 1003, 'Ac', 3, 1700.8, 5, NULL),
(4, 1003, 'Ac', 3, 1500.8, 3, NULL),
(4, 1003, 'Ac', 3, 1700.8, 5, NULL),
(5, 1003, 'Ac', 3, 1500.8, 3, NULL),
(5, 1003, 'Ac', 3, 1500.8, 3, NULL),
(5, 1003, 'Ac', 3, 1700.8, 5, NULL),
(6, 1005, 'Ac', 3, 1500.8, 3, NULL),
(6, 1005, 'Ac', 3, 1700.8, 5, NULL),
(7, 1006, 'Ac', 3, 1700.8, 5, NULL),
(7, 1006, 'Ac', 3, 1500.8, 3, NULL),
(7, 1006, 'Non/Ac', 3, 750.80, 7, NULL),
(8, 1006, 'Ac', 3, 1500.8, 3, NULL),
(8, 1006, 'Non/Ac', 3, 750.80, 3, NULL),
(9, 1006, 'Ac', 3, 1500.8, 3, NULL);

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS=1;

-- Select max price for HostelId=1
SELECT MAX(price) AS max FROM klr.Roomlists WHERE HostelId=1;

-- Update Roomlists table
UPDATE klr.Roomlists SET RmisDeleted=1, LastmodifiedByRM='me', LastmodifiedRM=NOW() WHERE HostelId=6;

-- Select all from Roomlists table
SELECT * FROM klr.Roomlists;

-- Update Hotels table with starting and ending prices for each hotel
UPDATE klr.Hotels SET StaringPr = (SELECT MIN(price) FROM klr.Roomlists WHERE HostelId=1) WHERE Hotelid=1;
UPDATE klr.Hotels SET EndPr = (SELECT MAX(price) FROM klr.Roomlists WHERE HostelId=1) WHERE Hotelid=1;

UPDATE klr.Hotels SET StaringPr = (SELECT MIN(price) FROM klr.Roomlists WHERE HostelId=2) WHERE Hotelid=2;
UPDATE klr.Hotels SET EndPr = (SELECT MAX(price) FROM klr.Roomlists WHERE HostelId=2) WHERE Hotelid=2;

UPDATE klr.Hotels SET StaringPr = (SELECT MIN(price) FROM klr.Roomlists WHERE HostelId=3) WHERE Hotelid=3;
UPDATE klr.Hotels SET EndPr = (SELECT MAX(price) FROM klr.Roomlists WHERE HostelId=3) WHERE Hotelid=3;

UPDATE klr.Hotels SET StaringPr = (SELECT MIN(price) FROM klr.Roomlists WHERE HostelId=4) WHERE Hotelid=4;
UPDATE klr.Hotels SET EndPr = (SELECT MAX(price) FROM klr.Roomlists WHERE HostelId=4) WHERE Hotelid=4;

UPDATE klr.Hotels SET StaringPr = (SELECT MIN(price) FROM klr.Roomlists WHERE HostelId=5) WHERE Hotelid=5;
UPDATE klr.Hotels SET EndPr = (SELECT MAX(price) FROM klr.Roomlists WHERE HostelId=5) WHERE Hotelid=5;

UPDATE klr.Hotels SET StaringPr = (SELECT MIN(price) FROM klr.Roomlists WHERE HostelId=6) WHERE Hotelid=6;
UPDATE klr.Hotels SET EndPr = (SELECT MAX(price) FROM klr.Roomlists WHERE HostelId=6) WHERE Hotelid=6;

UPDATE klr.Hotels SET StaringPr = (SELECT MIN(price) FROM klr.Roomlists WHERE HostelId=7) WHERE Hotelid=7;
UPDATE klr.Hotels SET EndPr = (SELECT MAX(price) FROM klr.Roomlists WHERE HostelId=7) WHERE Hotelid=7;

UPDATE klr.Hotels SET StaringPr = (SELECT MIN(price) FROM klr.Roomlists WHERE HostelId=8) WHERE Hotelid=8;
UPDATE klr.Hotels SET EndPr = (SELECT MAX(price) FROM klr.Roomlists WHERE HostelId=8) WHERE Hotelid=8;

UPDATE klr.Hotels SET StaringPr = (SELECT MIN(price) FROM klr.Roomlists WHERE HostelId=9) WHERE Hotelid=9;
UPDATE klr.Hotels SET EndPr = (SELECT MAX(price) FROM klr.Roomlists WHERE HostelId=9) WHERE Hotelid=9;

-- Select all from Roomlists table where Hotelid=9
SELECT * FROM klr.Roomlists WHERE HostelId=9;

-- Dummy select statement
SELECT 1;
-- BookingList

-- Disable foreign key checks to allow identity insert
SET FOREIGN_KEY_CHECKS=0;

-- Insert data into BookingLists table
INSERT INTO klr.BookingLists (id, Hostelid, Roomid, NoOfGuests, BookedDate, BookingFrom, BookingTo, TotalStay) VALUES
(1001, 2, 8, 3, NOW(), NOW(), DATE_ADD(CURDATE(), INTERVAL 3 DAY), 3);

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS=1;