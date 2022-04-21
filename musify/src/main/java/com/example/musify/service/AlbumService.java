package com.example.musify.service;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.dto.AlbumNewDTO;
import com.example.musify.dto.SongDTO;
import com.example.musify.entities.*;
import com.example.musify.exceptions.*;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.mapper.SongMapper;
import com.example.musify.repo.*;
import com.example.musify.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepo albumRepo;
    private final AlbumMapper albumMapper;
    private final SongRepo songRepo;
    private final ArtistRepo artistRepo;
    private final BandRepo bandRepo;
    private final PlaylistRepo playlistRepo;
    private final SongMapper songMapper;
    private final RepoValidation repoValidation;
    private final JWTUtils jwtUtils;

    @Autowired
    public AlbumService(AlbumRepo albumRepo, AlbumMapper albumMapper, SongRepo songRepo, ArtistRepo artistRepo, BandRepo bandRepo, PlaylistRepo playlistRepo, SongMapper songMapper, RepoValidation repoValidation, JWTUtils jwtUtils) {
        this.albumRepo = albumRepo;
        this.albumMapper = albumMapper;
        this.songRepo = songRepo;
        this.artistRepo = artistRepo;
        this.bandRepo = bandRepo;
        this.playlistRepo = playlistRepo;
        this.songMapper = songMapper;
        this.repoValidation = repoValidation;
        this.jwtUtils = jwtUtils;
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
        if ((artistId == null && bandId == null) || (artistId == 0 && bandId == 0)) {
            throw new InvalidArtistException("No artist or band introduced");
        }
        if ((artistId != null && bandId != null) && (artistId != 0 && bandId != 0)) {
            throw new InvalidArtistException("An album can not have both an artist and a band as owner");
        }

        Album newAlbum = albumMapper.toEntityNew(albumNewDTO);
        albumNewDTO.getSongIds()
                .stream()
                .map(repoValidation::checkSong)
                .forEach(newAlbum::addSong);

        albumRepo.save(newAlbum);
        if (artistId != null && artistId != 0) {
            newAlbum.setArtist(repoValidation.checkArtist(artistId));
        }
        if (bandId != null && bandId != 0) {
            newAlbum.setBand(repoValidation.checkBand(bandId));
        }
        return albumMapper.toDTO(albumRepo.save(newAlbum));

    }

    @Transactional
    public AlbumDTO removeAlbumById(Long id) {
        Album oldAlbum = repoValidation.checkAlbum(id);
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
        repoValidation.checkAlbum(albumNewDTO.getId());
        return saveAlbum(albumNewDTO);
    }

    @Transactional
    public AlbumDTO addToPlaylist(Long albumId, Long playlistId) throws UnauthorizedException {
        Album album = repoValidation.checkAlbum(albumId);
        Playlist playlist = repoValidation.checkPlaylist(playlistId);

        if (playlist.getOwnerUser().equals(jwtUtils.getUserId())) {
            throw new UnauthorizedException("Only the owner of the playlist can modify its content");
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        playlist.setLastUpdatedDate(localDateTime);
        for (Song song : album.getSongs()) {
            playlist.addSong(song);
        }
        playlistRepo.save(playlist);
        return albumMapper.toDTO(album);
    }

    @Transactional
    public List<SongDTO> getSongsFromAlbum(Long albumId) {
        Album album = repoValidation.checkAlbum(albumId);
        return songMapper.toDTOs(album.getSongs());
    }


}
