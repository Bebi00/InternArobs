

start transaction;
set global transaction  ISOLATION LEVEL READ UNCOMMITTED;
set autocommit=0;
INSERT INTO artist (artistFirstName,stageName,artistLastName,isFormation) VALUES ('Marshall','Eminem','Mathers',0);
select * from artist;
select sleep(10);
rollback;	



