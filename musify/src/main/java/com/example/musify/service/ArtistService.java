package com.example.musify.service;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.dto.ArtistDTO;
import com.example.musify.entities.Artist;
import com.example.musify.exceptions.ArtistNotFoundException;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.mapper.ArtistMapper;
import com.example.musify.repo.ArtistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepo artistRepo;
    private final ArtistMapper artistMapper;
    private final AlbumMapper albumMapper;

    @Autowired
    public ArtistService(ArtistRepo artistRepo, ArtistMapper artistMapper, AlbumMapper albumMapper) {
        this.artistRepo = artistRepo;
        this.artistMapper = artistMapper;
        this.albumMapper = albumMapper;
    }


    public List<ArtistDTO> getAll() {
        return artistMapper.toDTOs(artistRepo.findAll());
    }

    public ArtistDTO getByStageName(String stageName) {
        return artistMapper.toDTO(artistRepo.findByStageNameContaining(stageName));
    }

    public ArtistDTO getById(Long id) {
        return artistMapper.toDTO(artistRepo.findArtistById(id));
    }

    @Transactional
    public ArtistDTO saveArtist(ArtistDTO artistDTO) {
        Artist artist = artistRepo.save(artistMapper.toEntity(artistDTO));
        return artistMapper.toDTO(artist);
    }

    @Transactional
    public ArtistDTO removeById(Long id) {
        Artist oldArtist = artistRepo.getById(id);
        artistRepo.removeArtistById(id);
        return artistMapper.toDTO(oldArtist);
    }

    @Transactional
    public ArtistDTO updateById(ArtistDTO artistDTO){
        Artist artist = artistRepo.findArtistById(artistDTO.getId());
        if(artist == null){
            throw new ArtistNotFoundException("The Artist with the given ID was not found");
        }
       return saveArtist(artistDTO);
    }

    @Transactional
    public List<AlbumDTO> getAlbums(Long artisId){
            Artist artist = artistRepo.findArtistById(artisId);
            if(artist == null){
                throw new ArtistNotFoundException("The Artist with the given id was not found");
            }
            return albumMapper.toDTOs(artist.getAlbums());
    }

    @Transactional
    public List<ArtistDTO> searchArtistsByStageNameOrBand(String searchedString){
        return artistMapper.toDTOs(artistRepo.searchArtistsByStageNameOrBand(searchedString));
    }
}
