drop  table klr.Roomlists
create table klr.Roomlists(
    Roomid int identity(1,1) not null,
    HostelId int not null,
    id int not NULL,
    RoomType VARCHAR(10),
    BedCount int not null,
    price FLOAT,
    CanStaycount int not null,
    RmisDeleted int DEFAULT 0,
    LastmodifiedRM date ,
    LastmodifiedByRM Varchar(30) 
);

TRUNCATE TABLE klr.Roomlists
-- insert into klr.Roomlists (HostelId, id, RoomType, BedCount, CanStaycount )VALUES (?,?,?,?,?)
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

SELECT * from klr.Roomlists WHERE RmisDeleted=0

