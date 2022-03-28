import DataEntity.Songs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Optional;

public class SongsDao implements DAO<Songs> {
    BasicConnection basicConnection = BasicConnection.getInstance("jdbc:mysql://127.0.0.1:3306/music-shop", "Bebi", "Bebi00", 5);
    Connection connection;
    Songs song;

    public SongsDao() throws SQLException {
    }


    @Override
    public Optional<Songs> get(int id) {
        try {
            connection = basicConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * from songs WHERE idSongs ="+id;
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                song.setSongID(Integer.parseInt(resultSet.getString("idSongs")));
                song.setSongName(resultSet.getString("songName"));
                song.setSongTime(Integer.parseInt(resultSet.getString("songTime")));
                song.setArtists(Integer.parseInt(resultSet.getString("artists")));
                song.setAlbumID(Integer.parseInt(resultSet.getString("idAlbum")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return Optional.empty();
    }

    @Override
    public Collection<Songs> getAll() {
        return null;
    }

    @Override
    public int save(Songs songs) {
        return 0;
    }

    @Override
    public void update(Songs songs) {

    }

    @Override
    public void delete(Songs songs) {

    }
}
