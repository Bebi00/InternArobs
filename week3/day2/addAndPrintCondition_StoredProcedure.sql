CREATE DEFINER=`Bebi`@`%` PROCEDURE `addAndPrintCondition`(IN songName VARCHAR(50),IN songTime INT ,IN artistID INT ,IN albumID INT)
BEGIN


SELECT @songNames:= songName FROM songs WHERE idAlbum = albumID;
    
    IF(songName NOT IN (@songNames))
    THEN 
		INSERT INTO songs (songName,songTime,artists,idAlbum) VALUES (songName,songTime,artistID,albumID);
end IF;
SELECT 
    *
FROM
    songs
WHERE
    idAlbum = albumID;
END