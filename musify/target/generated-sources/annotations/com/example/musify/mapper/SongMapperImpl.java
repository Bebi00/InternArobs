package com.example.musify.mapper;

import com.example.musify.dto.SongDTO;
import com.example.musify.dto.SongNewDTO;
import com.example.musify.entities.Song;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-14T11:24:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class SongMapperImpl implements SongMapper {

    @Override
    public SongDTO toDTO(Song song) {
        if ( song == null ) {
            return null;
        }

        SongDTO songDTO = new SongDTO();

        songDTO.setId( song.getId() );
        songDTO.setTitle( song.getTitle() );
        songDTO.setContributorArtists( song.getContributorArtists() );
        songDTO.setDuration( song.getDuration() );
        songDTO.setCreationDate( song.getCreationDate() );

        return songDTO;
    }

    @Override
    public Song toEntity(SongDTO SongDTO) {
        if ( SongDTO == null ) {
            return null;
        }

        Song song = new Song();

        song.setId( SongDTO.getId() );
        song.setTitle( SongDTO.getTitle() );
        song.setContributorArtists( SongDTO.getContributorArtists() );
        song.setDuration( SongDTO.getDuration() );
        song.setCreationDate( SongDTO.getCreationDate() );

        return song;
    }

    @Override
    public Song toEntityNew(SongNewDTO songNewDTO) {
        if ( songNewDTO == null ) {
            return null;
        }

        Song song = new Song();

        song.setId( songNewDTO.getId() );
        song.setTitle( songNewDTO.getTitle() );
        song.setContributorArtists( songNewDTO.getContributorArtists() );
        song.setDuration( songNewDTO.getDuration() );
        song.setCreationDate( songNewDTO.getCreationDate() );

        return song;
    }

    @Override
    public List<SongDTO> toDTOs(List<Song> songs) {
        if ( songs == null ) {
            return null;
        }

        List<SongDTO> list = new ArrayList<SongDTO>( songs.size() );
        for ( Song song : songs ) {
            list.add( toDTO( song ) );
        }

        return list;
    }
}
