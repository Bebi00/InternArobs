package com.example.musify.service;

import com.example.musify.dto.ArtistDTO;
import com.example.musify.entities.Artist;
import com.example.musify.exceptions.InvalidArtistException;
import com.example.musify.mapper.ArtistMapper;
import com.example.musify.repo.ArtistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepo artistRepo;
    private final ArtistMapper artistMapper;

    @Autowired
    public ArtistService(ArtistRepo artistRepo, ArtistMapper artistMapper) {
        this.artistRepo = artistRepo;
        this.artistMapper = artistMapper;
    }


    public List<ArtistDTO> getAll() {
        return artistMapper.toDTOs(artistRepo.findAll());
    }

    public Optional<ArtistDTO> getByStageName(String stageName) {
        Optional<ArtistDTO> optionalArtistDTO = Optional.of(artistMapper.toDTO(artistRepo.findByStageName(stageName)));
        if (optionalArtistDTO.isPresent()) {
            return optionalArtistDTO;
        } else {
            try {
                throw new InvalidArtistException("Artist was not found");
            } catch (InvalidArtistException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    public Optional<ArtistDTO> addArtist(ArtistDTO artistDTO){
        Artist artist = artistRepo.save(artistMapper.toEntity(artistDTO));
        return Optional.of(artistMapper.toDTO(artist));
    }
}
