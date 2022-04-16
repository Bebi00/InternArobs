package com.example.musify.mapper;


import com.example.musify.dto.AlbumDTO;
import com.example.musify.dto.AlbumNewDTO;
import com.example.musify.entities.Album;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AlbumMapper {
    AlbumDTO toDTO(Album album);

    Album toEntity(AlbumDTO albumDTO);

    Album toEntityNew(AlbumNewDTO albumNewDTO);

    List<AlbumDTO> toDTOs(List<Album> albums);
}
