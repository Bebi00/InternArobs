package com.example.musify.service;

import com.example.musify.dto.PlaylistDTO;
import com.example.musify.dto.PlaylistNewDTO;
import com.example.musify.dto.SongDTO;
import com.example.musify.entities.Playlist;
import com.example.musify.entities.Song;
import com.example.musify.entities.User;
import com.example.musify.exceptions.*;
import com.example.musify.mapper.PlaylistMapper;
import com.example.musify.mapper.SongMapper;
import com.example.musify.repo.PlaylistRepo;
import com.example.musify.repo.SongRepo;
import com.example.musify.repo.UserRepo;
import com.example.musify.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepo playlistRepo;
    private final PlaylistMapper playlistMapper;
    private final UserRepo userRepo;
    private final SongRepo songRepo;
    private final SongMapper songMapper;
    private final JWTUtils jwtUtils;
    private final RepoValidation repoValidation;

    @Autowired
    public PlaylistService(PlaylistRepo playlistRepo, PlaylistMapper playlistMapper, UserRepo userRepo,
                           SongRepo songRepo, SongMapper songMapper, JWTUtils jwtUtils, RepoValidation repoValidation) {
        this.playlistRepo = playlistRepo;
        this.playlistMapper = playlistMapper;
        this.userRepo = userRepo;
        this.songRepo = songRepo;
        this.songMapper = songMapper;
        this.jwtUtils = jwtUtils;
        this.repoValidation = repoValidation;
    }

    @Transactional
    public PlaylistDTO getPlaylistById(Long id) {
        return playlistMapper.toDTO(playlistRepo.findPlaylistById(id));
    }

    @Transactional
    public PlaylistDTO savePlaylist(String name, String type) {
        Playlist newPlaylist = new Playlist();
        newPlaylist.setType(type);
        newPlaylist.setName(name);

        User user = repoValidation.checkLoggedUser();
        newPlaylist.addUser(user);
        newPlaylist.setOwnerUser(user.getId());
        newPlaylist.setCreatedDate(LocalDate.now());

        if (newPlaylist.getType().equals("public") || newPlaylist.getType().equals("private")) {
            userRepo.save(user);
            return playlistMapper.toDTO(playlistRepo.save(newPlaylist));
        } else {
            throw new InvalidPlaylistException("Invalid Type of the playlist");
        }

    }

    @Transactional
    public PlaylistDTO updatePlaylist(PlaylistNewDTO playlistNewDTO) {
        Playlist updatedPlaylist = repoValidation.checkPlaylist(playlistNewDTO.getId());

        if (!updatedPlaylist.getName().equals(playlistNewDTO.getName())) {
            updatedPlaylist.setName(playlistNewDTO.getName());
        }
        if (!updatedPlaylist.getType().equals(playlistNewDTO.getType())) {
            updatedPlaylist.setType(playlistNewDTO.getType());
        }
        if (!playlistNewDTO.getType().equals("public") || !playlistNewDTO.getType().equals("private")) {
            throw new InvalidPlaylistException("Invalid Type for the playlist");
        }

        // for getting the update time in real time
        LocalDateTime localDateTime = LocalDateTime.now();
        updatedPlaylist.setLastUpdatedDate(localDateTime);
        return playlistMapper.toDTO(playlistRepo.save(updatedPlaylist));
    }

    @Transactional
    public PlaylistDTO removePlaylistById(Long id) {
        Playlist oldPlaylist = repoValidation.checkPlaylist(id);
        oldPlaylist.getSongs().forEach(oldPlaylist::removeSong);
        oldPlaylist.getUsers().forEach(oldPlaylist::removeUser);
        playlistRepo.removePlaylistById(id);
        return playlistMapper.toDTO(oldPlaylist);
    }

    @Transactional
    public SongDTO removeSongFromPlaylistById(Long songId, Long playlistId) throws UnauthorizedException {
        Song song = repoValidation.checkSong(songId);
        Playlist playlist = repoValidation.checkPlaylist(playlistId);

        if (playlist.getOwnerUser().equals(jwtUtils.getUserId())) {
            throw new UnauthorizedException("Only the owner of the playlist can modify its content");
        }

        if (playlist.getSongs().contains(song)) {
            LocalDateTime localDateTime = LocalDateTime.now();
            playlist.setLastUpdatedDate(localDateTime);
            playlist.removeSong(song);
            return songMapper.toDTO(songRepo.save(song));
        } else {
            throw new SongNotFoundException("The song is not in the playlist");
        }
    }

    @Transactional
    public PlaylistDTO followPlaylist(Long id) throws UnauthorizedException {
        Playlist playlist = repoValidation.checkPlaylist(id);
        if (playlist.getType().equals("private")) {
            throw new InvalidPlaylistException("A private Playlist can not be followed");
        }
        User user = repoValidation.checkLoggedUser();
        if (playlist.getUsers().contains(user)) {
            throw new RepeatedPlaylistException("The user already follows this playlist");
        }
        if (!playlist.getType().equals("public")) {
            throw new UnauthorizedException("The playlist is not public.");
        }
        playlist.addUser(user);
        userRepo.update(user);
        return playlistMapper.toDTO(playlistRepo.save(playlist));
    }

    @Transactional
    public List<SongDTO> getSongsFromPlaylist(Long playlistId) {
        Playlist playlist = repoValidation.checkPlaylist(playlistId);
        User user = repoValidation.checkLoggedUser();
        if (!playlist.getType().equals("public")) {
            if (user.getId().equals(playlist.getOwnerUser())) {
                throw new UnauthorizedException("The playlist is not public.");
            }
        }
        return (songMapper.toDTOs(new LinkedList<>(playlist.getSongs())));
    }

    @Transactional
    public List<SongDTO> changeSongOrder(Long playlistId, Long songId, Integer oldPosition, Integer newPosition) {
        Playlist playlist = repoValidation.checkPlaylist(playlistId);
        Song song = repoValidation.checkSong(songId);
        User user = repoValidation.checkLoggedUser();
        if (user.getId().equals(playlist.getOwnerUser())) {
            throw new UnauthorizedException("Only the owner can modify the playlist.");
        }
        LinkedList<Song> songs = new LinkedList<>(playlist.getSongs());
        if (oldPosition < 1 || oldPosition > songs.size() || newPosition < 1 || newPosition > songs.size()) {
            throw new IllegalArgumentException("The positions of the song are not in range.");
        }
        if (songs.get(oldPosition - 1).getId() != songId) {
            throw new InvalidSongException("The song introduced is not in the correct position");
        }
        if (!oldPosition.equals(newPosition)) {
            song = songs.get(oldPosition - 1);
            songs.remove(song);
            songs.add(newPosition - 1, song);
            playlist.setSongs(songs);
//            playlistRepo.save(playlist);
        }
        return (songMapper.toDTOs((playlistRepo.save(playlist).getSongs())));
    }
}
