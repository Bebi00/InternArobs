CREATE PROCEDURE addAndPrint (IN songName VARCHAR(50),IN songTime INT ,IN artistID INT ,IN albumID INT)
BEGIN
		INSERT INTO songs (songName,songTime,artists,idAlbum) VALUES (songName,songTime,artistID,albumID);
SELECT 
    *
FROM
    songs
WHERE
    idAlbum = albumID;
END

