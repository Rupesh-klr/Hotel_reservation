-- create SCHEMA klr;

create table klr.ApprovalRequests(
ApprovalId int identity(1,1) not null,
ProofType int,
Proof varchar(20), 
AttachmentMessage VARCHAR (MAX),
id int not null,
closebyId int,
isClosed int DEFAULT 0,
closedReplay VARCHAR(150),
closedOn date
);

select * from klr.ApprovalRequests where isClosed=0;
--(?,?,?,?)

insert into klr.ApprovalRequests (ProofType,Proof,AttachmentMessage,id) VALUES 
(1,'PAN897867','i m real human being to live in chennai.please approve me!',1001),
(1,'PAN111111','i m real human being to live in chennai.please approve me!',1002),
(2,'ADH111111','i m real human being to live in chennai.please approve me!',1003),
(2,'ADHA33333','i m real human being to live in chennai.please approve me!',1001),
(1,'PAN222222','i m real human being to live in chennai.please approve me!',1003),
(3,'VOTER8888','i m real human being to live in chennai.please approve me!',1001),
(1,'PAN444444','i m real human being to live in chennai.please approve me!',1002),
(2,'ADHA444444','i m real human being to live in chennai.please approve me!',1001),
(3,'VOTER00000','i m real human being to live in chennai.please approve me!',1003),
(4,'PASSPORT0000','i m real human being to live in chennai.please approve me!',1007),
(5,'DRILIC000','i m real human being to live in chennai.please approve me!',1006)