package com.example.musify.mapper;

import com.example.musify.dto.ArtistDTO;
import com.example.musify.entities.Artist;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-16T11:12:21+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ArtistMapperImpl implements ArtistMapper {

    @Override
    public ArtistDTO toDTO(Artist artist) {
        if ( artist == null ) {
            return null;
        }

        ArtistDTO artistDTO = new ArtistDTO();

        if ( artist.getId() != null ) {
            artistDTO.setId( artist.getId() );
        }
        artistDTO.setFirstName( artist.getFirstName() );
        artistDTO.setLastName( artist.getLastName() );
        artistDTO.setStageName( artist.getStageName() );
        artistDTO.setBirthday( artist.getBirthday() );
        artistDTO.setStartDateActivePeriod( artist.getStartDateActivePeriod() );
        artistDTO.setEndDateActivePeriod( artist.getEndDateActivePeriod() );

        return artistDTO;
    }

    @Override
    public Artist toEntity(ArtistDTO artistDTO) {
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

    @Override
    public Artist toNewEntity(ArtistDTO artistDTO) {
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

    @Override
    public List<ArtistDTO> toDTOs(List<Artist> artists) {
        if ( artists == null ) {
            return null;
        }

        List<ArtistDTO> list = new ArrayList<ArtistDTO>( artists.size() );
        for ( Artist artist : artists ) {
            list.add( toDTO( artist ) );
        }

        return list;
    }
}
