CREATE DEFINER=`Bebi`@`%` PROCEDURE `getSongs`(IN artistID INT)
BEGIN
	declare songNm VARCHAR(50) default '';
    declare songNames VARCHAR(500) default'';
    declare finished INT default 0;
    declare artistFullName VARCHAR(100);
    declare artistFirst VARCHAR(50);
    declare artistLast VARCHAR(50);
    declare cursorSongs Cursor FOR SELECT songName from songs WHERE artists = artistID;
    DECLARE continue handler for not found set finished =1;
    open cursorSongs;
    getSongs: LOOP
		FETCH cursorSongs INTO songNm;
        IF finished =1 then
			leave getSongs;
		end if;
	set songNames = concat(songNm,'    ',songNames);
    end loop getSongs;
	select artistFirstName into artistFirst from artist where artistId = artistID LIMIT 1;
	select artistLastName into artistLast from artist where artistId = artistID LIMIT 1;
    set artistFullName = concat(artistLast,' ',artistFirst);
    set songNames = concat(songNames, ' by ',artistFullName);
    select songNames;
END