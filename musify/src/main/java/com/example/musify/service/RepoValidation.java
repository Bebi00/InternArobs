package com.example.musify.service;

import com.example.musify.entities.*;
import com.example.musify.exceptions.*;
import com.example.musify.repo.*;
import com.example.musify.security.JWTUtils;
import org.springframework.stereotype.Component;

@Component
public class RepoValidation {
    private final ArtistRepo artistRepo;
    private final BandRepo bandRepo;
    private final SongRepo songRepo;
    private final AlbumRepo albumRepo;
    private final PlaylistRepo playlistRepo;
    private final UserRepo userRepo;
    private final JWTUtils jwtUtils;

    public RepoValidation(ArtistRepo artistRepo, BandRepo bandRepo, SongRepo songRepo, AlbumRepo albumRepo,
                          PlaylistRepo playlistRepo, UserRepo userRepo, JWTUtils jwtUtils) {
        this.artistRepo = artistRepo;
        this.bandRepo = bandRepo;
        this.songRepo = songRepo;
        this.albumRepo = albumRepo;
        this.playlistRepo = playlistRepo;
        this.userRepo = userRepo;
        this.jwtUtils = jwtUtils;
    }


    public Artist checkArtist(Long artistId) {
        Artist artist = artistRepo.findArtistById(artistId);
        if (artist == null) {
            throw new ArtistNotFoundException(String.format("The Artist with ID = %d was not found", artistId));
        }
        return artist;
    }

    public Band checkBand(Long bandId) {
        Band band = bandRepo.findBandById(bandId);
        if (band == null) {
            throw new BandNotFoundException(String.format("The Band with ID = %d was not found", bandId));
        }
        return band;
    }

    public Song checkSong(Long songId) {
        Song song = songRepo.findSongById(songId);
        if (song == null) {
            throw new SongNotFoundException(String.format("The Song with ID = %d was not found", songId));
        }
        return song;
    }

    public Album checkAlbum(Long albumId) {
        Album album = albumRepo.findAlbumById(albumId);
        if (album == null) {
            throw new AlbumNotFoundException(String.format("The Album with ID = %d was not found", albumId));
        }
        return album;
    }

    public Playlist checkPlaylist(Long playlistId) {
        Playlist playlist = playlistRepo.findPlaylistById(playlistId);
        if (playlist == null) {
            throw new ArtistNotFoundException(String.format("The Playlist with ID = %d was not found", playlistId));
        }
        return playlist;
    }

    public User checkUser(){
        return userRepo.getById(jwtUtils.getUserId())
                .orElseThrow(() -> new InvalidUserException(
                        String.format("User with the ID = %d was not found", jwtUtils.getUserId())));
    }
}
