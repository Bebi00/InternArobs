package com.example.musify.service;

import com.example.musify.dto.BandDTO;
import com.example.musify.dto.BandNewDTO;
import com.example.musify.entities.Band;
import com.example.musify.mapper.ArtistMapper;
import com.example.musify.mapper.BandMapper;
import com.example.musify.repo.ArtistRepo;
import com.example.musify.repo.BandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BandService {
    private final BandRepo bandRepo;
    private final BandMapper bandMapper;
    private final ArtistMapper artistMapper;
    private final ArtistRepo artistRepo;

    @Autowired
    public BandService(BandRepo bandRepo, BandMapper bandMapper, ArtistMapper artistMapper, ArtistRepo artistRepo) {
        this.bandRepo = bandRepo;
        this.bandMapper = bandMapper;
        this.artistMapper = artistMapper;
        this.artistRepo = artistRepo;
    }


    public List<BandDTO> getAll() {
        return bandMapper.toDTOs(bandRepo.findAll());
    }

    public Optional<BandDTO> getByBandName(String bandName) {
        return Optional.of(bandMapper.toDTO(bandRepo.findByBandName(bandName)));
    }

    @Transactional
    public Optional<BandDTO> saveBand(BandNewDTO bandDTO) {
        Band newBand = bandMapper.toEntityNew(bandDTO);
        Band band = bandRepo.save(newBand);
        return Optional.of(bandMapper.toDTO(band));
    }

    @Transactional
    public Optional<BandDTO> removeById(Long id) {
        Band oldBand = bandRepo.getById(id);
        bandRepo.removeBandById(id);
        return Optional.of(bandMapper.toDTO(oldBand));
    }

    @Transactional
    public Optional<BandDTO> updateById(BandNewDTO bandDTO){
       return saveBand(bandDTO);
    }
}
