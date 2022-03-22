CREATE DEFINER=`Bebi`@`%` PROCEDURE `addAndPrintTest`(IN songName VARCHAR(50),IN songTime INT ,IN artistID INT ,IN albumID INT)
BEGIN


SELECT @songNames:= songName FROM songs WHERE idAlbum = albumID;
END