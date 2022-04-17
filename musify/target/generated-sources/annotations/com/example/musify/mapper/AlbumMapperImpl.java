package com.example.musify.mapper;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.dto.AlbumNewDTO;
import com.example.musify.entities.Album;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-17T23:41:50+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class AlbumMapperImpl implements AlbumMapper {

    @Override
    public AlbumDTO toDTO(Album album) {
        if ( album == null ) {
            return null;
        }

        AlbumDTO albumDTO = new AlbumDTO();

        albumDTO.setReleaseDate( album.getReleaseDate() );
        albumDTO.setId( album.getId() );
        albumDTO.setTitle( album.getTitle() );
        albumDTO.setDescription( album.getDescription() );
        albumDTO.setGenre( album.getGenre() );
        albumDTO.setLabel( album.getLabel() );

        return albumDTO;
    }

    @Override
    public Album toEntity(AlbumDTO albumDTO) {
        if ( albumDTO == null ) {
            return null;
        }

        Album album = new Album();

        album.setId( albumDTO.getId() );
        album.setTitle( albumDTO.getTitle() );
        album.setDescription( albumDTO.getDescription() );
        album.setGenre( albumDTO.getGenre() );
        album.setReleaseDate( albumDTO.getReleaseDate() );
        album.setLabel( albumDTO.getLabel() );

        return album;
    }

    @Override
    public Album toEntityNew(AlbumNewDTO albumNewDTO) {
        if ( albumNewDTO == null ) {
            return null;
        }

        Album album = new Album();

        album.setId( albumNewDTO.getId() );
        album.setTitle( albumNewDTO.getTitle() );
        album.setDescription( albumNewDTO.getDescription() );
        album.setGenre( albumNewDTO.getGenre() );
        album.setReleaseDate( albumNewDTO.getReleaseDate() );
        album.setLabel( albumNewDTO.getLabel() );

        return album;
    }

    @Override
    public List<AlbumDTO> toDTOs(List<Album> albums) {
        if ( albums == null ) {
            return null;
        }

        List<AlbumDTO> list = new ArrayList<AlbumDTO>( albums.size() );
        for ( Album album : albums ) {
            list.add( toDTO( album ) );
        }

        return list;
    }
}
