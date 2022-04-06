package com.example.musify.service;

import com.example.musify.dto.ArtistDTO;
import com.example.musify.dto.UserDTO;
import com.example.musify.entities.Artist;
import com.example.musify.entities.User;

public interface ArtistMapper {
    UserDTO toDTO(Artist artist);

    User toEntity(ArtistDTO artistDTO);

    User toNewEntity(ArtistDTO artistDTO);
}
