package com.example.musify.mapper;

import com.example.musify.dto.ArtistDTO;
import com.example.musify.dto.SongDTO;
import com.example.musify.dto.SongNewDTO;
import com.example.musify.entities.Artist;
import com.example.musify.entities.Song;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-15T11:23:13+0300",
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
        songDTO.setDuration( song.getDuration() );
        songDTO.setCreationDate( song.getCreationDate() );
        songDTO.setOrderInAlbum( song.getOrderInAlbum() );

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
        song.setDuration( SongDTO.getDuration() );
        song.setCreationDate( SongDTO.getCreationDate() );
        song.setOrderInAlbum( SongDTO.getOrderInAlbum() );

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
        song.setDuration( songNewDTO.getDuration() );
        song.setCreationDate( songNewDTO.getCreationDate() );
        song.setOrderInAlbum( songNewDTO.getOrderInAlbum() );
        song.setArtists( artistDTOSetToArtistSet( songNewDTO.getArtists() ) );

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

    protected Artist artistDTOToArtist(ArtistDTO artistDTO) {
        if ( artistDTO == null ) {
            return null;
        }

        Artist artist = new Artist();

        artist.setId( artistDTO.getId() );
        artist.setFirstName( artistDTO.getFirstName() );
        artist.setLastName( artistDTO.getLastName() );
        artist.setStageName( artistDTO.getStageName() );
        artist.setBirthday( artistDTO.getBirthday() );
        artist.setStartDateActivePeriod( artistDTO.getStartDateActivePeriod() );
        artist.setEndDateActivePeriod( artistDTO.getEndDateActivePeriod() );

        return artist;
    }

    protected Set<Artist> artistDTOSetToArtistSet(Set<ArtistDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Artist> set1 = new HashSet<Artist>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ArtistDTO artistDTO : set ) {
            set1.add( artistDTOToArtist( artistDTO ) );
        }

        return set1;
    }
}
