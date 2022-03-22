drop TRIGGER triggerInsert;
DELIMITER $$

CREATE TRIGGER triggerInsert AFTER  INSERT on songs  for each row
begin
insert into songs_maintenance (songID,dateAdded) VALUES (NEW.idSongs,current_timestamp());
end$$

DELIMITER ;