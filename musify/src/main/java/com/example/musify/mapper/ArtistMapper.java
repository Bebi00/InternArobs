package com.example.musify.mapper;


import com.example.musify.dto.ArtistDTO;
import com.example.musify.entities.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ArtistMapper {
    ArtistDTO toDTO(Artist artist);

    Artist toEntity(ArtistDTO artistDTO);

    Artist toNewEntity(ArtistDTO artistDTO);

    List<ArtistDTO> toDTOs(List<Artist> artists);
}
