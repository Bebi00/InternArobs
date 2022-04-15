package com.example.musify.mapper;


import com.example.musify.dto.SongDTO;
import com.example.musify.dto.SongNewDTO;
import com.example.musify.entities.Song;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.WARN)
public interface SongMapper {
    SongDTO toDTO(Song song);

    Song toEntity(SongDTO SongDTO);

    Song toEntityNew(SongNewDTO songNewDTO);

    List<SongDTO> toDTOs(List<Song> songs);
}
