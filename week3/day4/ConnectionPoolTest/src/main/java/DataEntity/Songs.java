package DataEntity;

public class Songs {
    private int songID;
    private String songName;
    private int songTime;
    private int artists;
    private int albumID;

    Songs(int songID,String songName,int songTime,int artists,int albumID){
        this.albumID=albumID;
        this.artists = artists;
        this.songID = songID;
        this.songName = songName;
        this.songTime = songTime;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public void setArtists(int artists) {
        this.artists = artists;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setSongTime(int songTime) {
        this.songTime = songTime;
    }

    public int getAlbumID() {
        return albumID;
    }

    public int getArtists() {
        return artists;
    }

    public int getSongID() {
        return songID;
    }

    public String getSongName() {
        return songName;
    }

    public int getSongTime() {
        return songTime;
    }
}
