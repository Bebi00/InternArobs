set autocommit=0;
start transaction;
INSERT INTO artist (artistFirstName,stageName,artistLastName,isFormation) VALUES ('Marshall','Eminem','Mathers',0);
select * from artist;
select sleep(60);
rollback;



