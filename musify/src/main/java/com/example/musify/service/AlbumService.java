package com.example.musify.service;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.dto.AlbumNewDTO;
import com.example.musify.entities.Album;
import com.example.musify.entities.Song;
import com.example.musify.exceptions.InvalidArtistException;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.repo.AlbumRepo;
import com.example.musify.repo.ArtistRepo;
import com.example.musify.repo.BandRepo;
import com.example.musify.repo.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepo albumRepo;
    private final AlbumMapper albumMapper;
    private final SongRepo songRepo;
    private final ArtistRepo artistRepo;
    private final BandRepo bandRepo;

    @Autowired
    public AlbumService(AlbumRepo albumRepo, AlbumMapper albumMapper, SongRepo songRepo, ArtistRepo artistRepo, BandRepo bandRepo) {
        this.albumRepo = albumRepo;
        this.albumMapper = albumMapper;
        this.songRepo = songRepo;
        this.artistRepo = artistRepo;
        this.bandRepo = bandRepo;
    }


    public List<AlbumDTO> getAll() {
        return albumMapper.toDTOs(albumRepo.findAll());
    }

    public AlbumDTO getAlbumByTitle(String title) {
        return albumMapper.toDTO(albumRepo.findAlbumByTitle(title));
    }

    public AlbumDTO getAlbumById(Long id) {
        return albumMapper.toDTO(albumRepo.getById(id));
    }

    @Transactional
    public AlbumDTO saveAlbum(AlbumNewDTO albumNewDTO) throws InvalidArtistException {
        Long artistId = albumNewDTO.getArtistId();
        Long bandId = albumNewDTO.getBandId();
        if (artistId == null && bandId == null) {
            throw new InvalidArtistException("No artist or band introduced");
        }
        if (artistId != null && bandId != null) {
            throw new InvalidArtistException("An album can not have both an artist and a band as owner");
        }
        Album newAlbum = albumMapper.toEntityNew(albumNewDTO);
        albumNewDTO.getSongIds()
                .stream()
                .map(songRepo::getById)
                .forEach(newAlbum::addSong);

        if (artistId != null) {
            newAlbum.setArtist(artistRepo.findArtistById(artistId));
        }
        if (bandId != null) {
            newAlbum.setBand(bandRepo.getById(bandId));
        }
        return albumMapper.toDTO(albumRepo.save(newAlbum));

    }

    @Transactional
    public AlbumDTO removeAlbumById(Long id) {
        Album oldAlbum = albumRepo.getById(id);
        if (oldAlbum.getBand() != null) {
            oldAlbum.removeBand(oldAlbum.getBand());
        }
        if (oldAlbum.getArtist() != null) {
            oldAlbum.removeArtist(oldAlbum.getArtist());
        }
        List<Song> songs = new ArrayList<>(oldAlbum.getSongs());
        songs.forEach(oldAlbum::removeSong);

        albumRepo.removeAlbumById(id);
        return albumMapper.toDTO(oldAlbum);
    }

    @Transactional
    public AlbumDTO updateAlbumById(AlbumNewDTO albumNewDTO) throws InvalidArtistException {
        return saveAlbum(albumNewDTO);
    }
}
