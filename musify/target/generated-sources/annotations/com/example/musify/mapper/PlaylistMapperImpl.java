package com.example.musify.mapper;

import com.example.musify.dto.PlaylistDTO;
import com.example.musify.dto.PlaylistNewDTO;
import com.example.musify.entities.Playlist;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-18T10:35:33+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class PlaylistMapperImpl implements PlaylistMapper {

    @Override
    public Playlist toEntity(PlaylistNewDTO playlistNewDTO) {
        if ( playlistNewDTO == null ) {
            return null;
        }

        Playlist playlist = new Playlist();

        playlist.setId( playlistNewDTO.getId() );
        playlist.setName( playlistNewDTO.getName() );
        playlist.setType( playlistNewDTO.getType() );

        return playlist;
    }

    @Override
    public Playlist toEntity(PlaylistDTO playlistDTO) {
        if ( playlistDTO == null ) {
            return null;
        }

        Playlist playlist = new Playlist();

        playlist.setId( playlistDTO.getId() );
        playlist.setName( playlistDTO.getName() );
        playlist.setOwnerUser( playlistDTO.getOwnerUser() );
        playlist.setType( playlistDTO.getType() );
        playlist.setCreatedDate( playlistDTO.getCreatedDate() );
        playlist.setLastUpdatedDate( playlistDTO.getLastUpdatedDate() );

        return playlist;
    }

    @Override
    public PlaylistDTO toDTO(Playlist playlist) {
        if ( playlist == null ) {
            return null;
        }

        PlaylistDTO playlistDTO = new PlaylistDTO();

        if ( playlist.getId() != null ) {
            playlistDTO.setId( playlist.getId() );
        }
        playlistDTO.setName( playlist.getName() );
        playlistDTO.setType( playlist.getType() );
        playlistDTO.setCreatedDate( playlist.getCreatedDate() );
        playlistDTO.setOwnerUser( playlist.getOwnerUser() );
        playlistDTO.setLastUpdatedDate( playlist.getLastUpdatedDate() );

        return playlistDTO;
    }

    @Override
    public List<PlaylistDTO> toDTOs(List<Playlist> playlists) {
        if ( playlists == null ) {
            return null;
        }

        List<PlaylistDTO> list = new ArrayList<PlaylistDTO>( playlists.size() );
        for ( Playlist playlist : playlists ) {
            list.add( toDTO( playlist ) );
        }

        return list;
    }
}
