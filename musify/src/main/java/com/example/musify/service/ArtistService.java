package com.example.musify.service;

import com.example.musify.dto.ArtistDTO;
import com.example.musify.entities.Artist;
import com.example.musify.mapper.ArtistMapper;
import com.example.musify.repo.ArtistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return Optional.of(artistMapper.toDTO(artistRepo.findByStageName(stageName)));
    }

    public Optional<ArtistDTO> getById(Long id) {
        return Optional.of(artistMapper.toDTO(artistRepo.findArtistById(id)));
    }

    @Transactional
    public Optional<ArtistDTO> saveArtist(ArtistDTO artistDTO) {
        Artist artist = artistRepo.save(artistMapper.toEntity(artistDTO));
        return Optional.of(artistMapper.toDTO(artist));
    }

    @Transactional
    public Optional<ArtistDTO> removeById(Long id) {
        Artist oldArtist = artistRepo.getById(id);
        artistRepo.removeArtistById(id);
        return Optional.of(artistMapper.toDTO(oldArtist));
    }

    @Transactional
    public Optional<ArtistDTO> updateById(ArtistDTO artistDTO){
       return saveArtist(artistDTO);
    }
}
