package com.example.musify.dto;

import com.example.musify.entities.Playlist;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PlaylistRowMapper implements RowMapper<Playlist> {
    public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {

        LocalDate createdDate = rs.getDate("created_date").toLocalDate();
        String date = rs.getString("last_updated_date");

        Playlist playlist = new Playlist();
        playlist.setId(rs.getLong("id"));
        playlist.setName( rs.getString("name"));
        playlist.setOwnerUser(rs.getLong("owner_user"));
        playlist.setType(rs.getString("type"));
        playlist.setCreatedDate(createdDate);
        if(date != null){
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            playlist.setLastUpdatedDate(LocalDateTime.parse(date.substring(0,19),formatter));
        }
        return playlist;
    }
}
