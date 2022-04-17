package com.example.musify.mapper;

import com.example.musify.dto.PlaylistDTO;
import com.example.musify.dto.PlaylistNewDTO;
import com.example.musify.entities.Playlist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    Playlist toEntity(PlaylistNewDTO playlistNewDTO);
    Playlist toEntity(PlaylistDTO playlistDTO);
    PlaylistDTO toDTO(Playlist playlist);
    List<PlaylistDTO> toDTOs(List<Playlist> playlists);
}
