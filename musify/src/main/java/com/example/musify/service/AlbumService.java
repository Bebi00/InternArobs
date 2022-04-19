package com.example.musify.service;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.dto.AlbumNewDTO;
import com.example.musify.entities.Album;
import com.example.musify.entities.Playlist;
import com.example.musify.entities.Song;
import com.example.musify.exceptions.AlbumNotFoundException;
import com.example.musify.exceptions.InvalidArtistException;
import com.example.musify.exceptions.SongNotFoundException;
import com.example.musify.exceptions.UnauthorizedException;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {
    private final AlbumRepo albumRepo;
    private final AlbumMapper albumMapper;
    private final SongRepo songRepo;
    private final ArtistRepo artistRepo;
    private final BandRepo bandRepo;
    private final PlaylistRepo playlistRepo;

    @Autowired
    public AlbumService(AlbumRepo albumRepo, AlbumMapper albumMapper, SongRepo songRepo, ArtistRepo artistRepo, BandRepo bandRepo, PlaylistRepo playlistRepo) {
        this.albumRepo = albumRepo;
        this.albumMapper = albumMapper;
        this.songRepo = songRepo;
        this.artistRepo = artistRepo;
        this.bandRepo = bandRepo;
        this.playlistRepo = playlistRepo;
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
        if ((artistId == null && bandId == null) || (artistId == 0 && bandId == 0)){
            throw new InvalidArtistException("No artist or band introduced");
        }
        if ((artistId != null && bandId != null) && (artistId != 0 && bandId != 0)) {
            throw new InvalidArtistException("An album can not have both an artist and a band as owner");
        }
        Album newAlbum = albumMapper.toEntityNew(albumNewDTO);
        List<Song> songs = albumNewDTO.getSongIds()
                .stream()
                .map(songRepo::findSongById)
                .collect(Collectors.toList());
        for(Song song:songs){
            newAlbum.addSong(song);
        }
        albumRepo.save(newAlbum);
        if (artistId != null && artistId !=0) {
            newAlbum.setArtist(artistRepo.findArtistById(artistId));
        }
        if (bandId != null && bandId != 0) {
            newAlbum.setBand(bandRepo.findBandById(bandId));
        }
        return albumMapper.toDTO(albumRepo.save(newAlbum));

    }

    @Transactional
    public AlbumDTO removeAlbumById(Long id) {
        Album oldAlbum = albumRepo.findAlbumById(id);
        if(oldAlbum == null){
            throw new AlbumNotFoundException("The Album with the given ID was not found");
        }
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

    @Transactional
    public AlbumDTO addToPlaylist(Long albumId, Long playlistId) throws UnauthorizedException {
        Album album = albumRepo.findAlbumById(albumId);
        Playlist playlist = playlistRepo.findPlaylistById(playlistId);
        if(playlist == null){
            throw new SongNotFoundException("Playlist with the given id was not found");
        }
        List<?> userInfo = (List<?>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(playlist.getOwnerUser() != (int) userInfo.get(0)){
            throw new UnauthorizedException("Only the owner of the playlist can modify its content");
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        playlist.setLastUpdatedDate(localDateTime);
        for (Song song:album.getSongs()){
            song.addPlaylist(playlist);
            songRepo.save(song);
        }
        return albumMapper.toDTO(album);
    }
}
