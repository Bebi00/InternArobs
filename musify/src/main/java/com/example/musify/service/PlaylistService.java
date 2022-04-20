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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PlaylistService {
    private final PlaylistRepo playlistRepo;
    private final PlaylistMapper playlistMapper;
    private final UserRepo userRepo;
    private final SongRepo songRepo;
    private final SongMapper songMapper;

    @Autowired
    public PlaylistService(PlaylistRepo playlistRepo, PlaylistMapper playlistMapper, UserRepo userRepo, SongRepo songRepo, SongMapper songMapper) {
        this.playlistRepo = playlistRepo;
        this.playlistMapper = playlistMapper;
        this.userRepo = userRepo;
        this.songRepo = songRepo;
        this.songMapper = songMapper;
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
        List<?> userInfo = (List<?>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.getById((Integer) userInfo.get(0)).get();
        newPlaylist.addUser(user);
        newPlaylist.setOwnerUser(Long.valueOf(user.getId()));
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(calendar.getTime());
        newPlaylist.setCreatedDate(LocalDate.parse(date));


        if (newPlaylist.getType().equals("public") || newPlaylist.getType().equals("private")) {
            userRepo.save(user);
            return playlistMapper.toDTO(playlistRepo.save(newPlaylist));
        } else {
            throw new InvalidPlaylistException("Invalid Type of the playlist");
        }

    }

    @Transactional
    public PlaylistDTO updatePlaylist(PlaylistNewDTO playlistNewDTO) {
        Playlist updatedPlaylist = playlistRepo.findPlaylistById(playlistNewDTO.getId());
        if (updatedPlaylist == null) {
            throw new InvalidPlaylistException();
        }

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
        Playlist oldPlaylist = playlistRepo.findPlaylistById(id);
        if (oldPlaylist == null) {
            throw new InvalidPlaylistException();
        }
        playlistRepo.removePlaylistById(id);
        return playlistMapper.toDTO(oldPlaylist);
    }

    @Transactional
    public SongDTO removeSongFromPlaylistById(Long songId, Long playlistId) throws UnauthorizedException {
        List<?> userInfo = (List<?>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Song song = songRepo.findSongById(songId);
        Playlist playlist = playlistRepo.findPlaylistById(playlistId);
        if (playlist == null) {
            throw new SongNotFoundException("Playlist with the given id was not found");
        }
        if (playlist.getOwnerUser() != (int) userInfo.get(0)) {
            throw new UnauthorizedException("Only the owner of the playlist can modify its content");
        }
        if (song == null) {
            throw new SongNotFoundException("Song with the given id was not found");
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
        Playlist playlist = playlistRepo.findPlaylistById(id);
        if (playlist == null) {
            throw new SongNotFoundException("Playlist with the given id was not found");
        }
        if(playlist.getType().equals("private")){
            throw new InvalidPlaylistException("A private Playlist can not be followed");
        }
        List<?> userInfo = (List<?>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.getById((int) userInfo.get(0)).get();
        if(playlist.getUsers().contains(user)){
            throw new RepeatedPlaylistException("The user already follows this playlist");
        }
        if (!playlist.getType().equals("public")){
            throw new UnauthorizedException("The playlist is not public.");
        }
        playlist.addUser(user);
        userRepo.update(user);
        return playlistMapper.toDTO(playlistRepo.save(playlist));
    }

    @Transactional
    public List<SongDTO> getSongsFromPlaylist(Long playlistId){
        Playlist playlist = playlistRepo.findPlaylistById(playlistId);
        if (playlist == null) {
            throw new SongNotFoundException("Playlist with the given id was not found");
        }
        List<?> userInfo = (List<?>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.getById((Integer) userInfo.get(0)).get();
        if (!playlist.getType().equals("public")){
            if ((int)user.getId() != playlist.getOwnerUser()) {
                throw new UnauthorizedException("The playlist is not public.");
            }
        }

        return (songMapper.toDTOs(new LinkedList<>(playlist.getSongs())));
    }

    @Transactional
    public List<SongDTO> changeSongOrder(Long playlistId,Long songId,Integer oldPosition,Integer newPosition){
        Playlist playlist = playlistRepo.findPlaylistById(playlistId);
        Song song = songRepo.findSongById(songId);
        if (playlist == null) {
            throw new SongNotFoundException("Playlist with the given id was not found");
        }
        if (song == null) {
            throw new SongNotFoundException("Song with the given id was not found");
        }
        List<?> userInfo = (List<?>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.getById((Integer) userInfo.get(0)).get();
        if ((int)user.getId() != playlist.getOwnerUser()) {
            throw new UnauthorizedException("Only the owner can modify the playlist.");
        }
        LinkedList<Song> songs = new LinkedList<>(playlist.getSongs());
        if(oldPosition < 1 || oldPosition > songs.size() || newPosition < 1 || newPosition > songs.size() ){
            throw new IllegalArgumentException("The positions of the song are not in range.");
        }
        if(songs.get(oldPosition-1).getId() != songId){
            throw new InvalidSongException("The song introduced is not in the correct position");
        }
        if(!oldPosition.equals(newPosition)){
            song = songs.get(oldPosition-1);
            songs.remove(song);
            songs.add(newPosition-1,song);
            playlist.setSongs(songs);
//            playlistRepo.save(playlist);
        }
        return (songMapper.toDTOs((playlistRepo.save(playlist).getSongs())));
    }
}
