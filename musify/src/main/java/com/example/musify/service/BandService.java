package com.example.musify.service;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.dto.ArtistDTO;
import com.example.musify.dto.BandDTO;
import com.example.musify.dto.BandNewDTO;
import com.example.musify.entities.Artist;
import com.example.musify.entities.Band;
import com.example.musify.exceptions.ArtistNotFoundException;
import com.example.musify.exceptions.BandNotFoundException;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.mapper.ArtistMapper;
import com.example.musify.mapper.BandMapper;
import com.example.musify.repo.ArtistRepo;
import com.example.musify.repo.BandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BandService {
    private final BandRepo bandRepo;
    private final BandMapper bandMapper;
    private final ArtistMapper artistMapper;
    private final ArtistRepo artistRepo;
    private final AlbumMapper albumMapper;

    @Autowired
    public BandService(BandRepo bandRepo, BandMapper bandMapper, ArtistMapper artistMapper, ArtistRepo artistRepo, AlbumMapper albumMapper) {
        this.bandRepo = bandRepo;
        this.bandMapper = bandMapper;
        this.artistMapper = artistMapper;
        this.artistRepo = artistRepo;
        this.albumMapper = albumMapper;
    }


    public List<BandDTO> getAll() {
        return bandMapper.toDTOs(bandRepo.findAll());
    }

    public BandDTO getByBandName(String bandName) {
        return bandMapper.toDTO(bandRepo.findBandByBandName(bandName));
    }

    @Transactional
    public BandDTO saveBand(BandNewDTO bandNewDTO) {
        Band newBand = bandMapper.toEntityNew(bandNewDTO);
        bandNewDTO.getArtistsIds()
                .stream()
                .map(artistRepo::findArtistById)
                .forEach(artist -> {
                    if (artist == null) {
                        throw new ArtistNotFoundException("The artist with the given ID was not found");
                    }
                    newBand.addArtist(artist);
                });
        return bandMapper.toDTO(bandRepo.save(newBand));
    }

    @Transactional
    public BandDTO removeById(Long id) {
        Band oldBand = bandRepo.findBandById(id);
        List<Artist> artists = new ArrayList<>(oldBand.getArtists());
        artists.forEach(oldBand::removeArtist);
        bandRepo.removeBandById(id);
        return bandMapper.toDTO(oldBand);
    }

    @Transactional
    public BandDTO updateById(BandNewDTO bandDTO) {
        Band band = bandRepo.findBandById(bandDTO.getId());
        if (band == null) {
            throw new BandNotFoundException("The Band with the given ID was not found");
        }

        return saveBand(bandDTO);
    }

    @Transactional
    public List<AlbumDTO> getAlbums(Long bandId) {
        Band band = bandRepo.findBandById(bandId);
        if (band == null) {
            throw new BandNotFoundException("The Band with the given id was not found");
        }
        return albumMapper.toDTOs(band.getAlbums());
    }

    @Transactional
    public List<BandDTO> searchBandByBandNameOrArtist(String searchedString){
        return bandMapper.toDTOs(bandRepo.searchBandByBandNameOrArtists(searchedString));
    }
}
